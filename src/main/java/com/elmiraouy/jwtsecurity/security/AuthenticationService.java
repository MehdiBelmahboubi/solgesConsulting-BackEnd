package com.elmiraouy.jwtsecurity.security;

import com.elmiraouy.jwtsecurity.Dto.request.AppUserRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.AppUserResponseDto;
import com.elmiraouy.jwtsecurity.Dto.response.CompanyResponseDto;
import com.elmiraouy.jwtsecurity.entities.AppRole;
import com.elmiraouy.jwtsecurity.entities.AppUser;
import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.entities.Token;
import com.elmiraouy.jwtsecurity.handlerException.AppUserException;
import com.elmiraouy.jwtsecurity.mail.MailService;
import com.elmiraouy.jwtsecurity.mail.MailStructure;
import com.elmiraouy.jwtsecurity.mappers.AppUserDtoMapper;
import com.elmiraouy.jwtsecurity.mappers.CompanyDtoMapper;
import com.elmiraouy.jwtsecurity.repository.AppRoleRepository;
import com.elmiraouy.jwtsecurity.repository.AppUserRepository;
import com.elmiraouy.jwtsecurity.repository.TokenRepository;
import com.elmiraouy.jwtsecurity.service.AppUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ConnectException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final  AppRoleRepository appRoleRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;
    private final MailService mailService;
    private final AppUserDtoMapper appUserDtoMapper;
    private final CompanyDtoMapper companyDtoMapper;

    public AppUserResponseDto register(AppUserRequestDto request) throws AppUserException, ConnectException {

        Optional<AppUser> userByEmail = appUserRepository.findByEmail(request.getEmail());
        if(userByEmail.isPresent())
            throw new AppUserException("un utilisateur deja inscrit avec cet email: [%s] :".formatted(request.getEmail()));
        var user = AppUser.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .dateCreation(LocalDateTime.now())
                .build();
        AppUser saveUser = appUserRepository.save(user);
        AppUserResponseDto response=AppUserResponseDto.builder().build();
        BeanUtils.copyProperties(saveUser,response);

        return sendUuidToUser(request);
    }


    public AppUserResponseDto authenticate(String email,String passWord) throws AppUserException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                email,
                passWord
        ));
        var user= appUserRepository.findByEmail(email)
                .orElseThrow(() -> new AppUserException(
                        "User With id [%s] not Exist".formatted(email)));;
        var jwtToken = jwtService.generateToken(user);
        var jwtRefreshToken = jwtService.generateRefreshToken(user);

        // todo  here change old token by new token
        Token token=null;
        token=tokenRepository.findByAppUserId(user.getId());
        if (token == null){
            token=new Token();
        }
            token.setRefreshToken(jwtRefreshToken);
            token.setRevoked(false);
            token.setExpired(false);
            token.setAccessToken(jwtToken);
            token.setAppUser(user);
            user.setToken(token);
            appUserService.addTokenToUser(user,tokenRepository.save(token));

        System.out.println("  -------------------- Authenticate ");
        AppUser saveUser = appUserRepository.save(user);
        AppUserResponseDto appUserResponseDto = appUserDtoMapper.appUserToDto(saveUser);
        appUserResponseDto.setUrlImage(user.getImage().getUrl());
        Collection<Company> companies = user.getCompanies();
        System.out.println("companies lenght : "+companies.isEmpty());
        List<CompanyResponseDto> companyResponseDtoStream = companies.stream().map(companyDtoMapper::companyToDto).toList();
        System.out.println("companies lenght : "+ (long) companyResponseDtoStream.size());
        companyResponseDtoStream.forEach(companyResponseDto ->
                System.out.println("compan ::::"+companyResponseDto.getName()));
        appUserResponseDto.setCompanies(companyResponseDtoStream);
        return appUserResponseDto ;
    }

    public AppUserResponseDto refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader =request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail ;
        if(authHeader == null  || !authHeader.startsWith("Bearer ")){
            System.out.println("refresh tok,ne !: Berer est null !!!!");
            return null;
        }
        refreshToken =authHeader.substring(7);
        userEmail= jwtService.extractUserEmail(refreshToken);
        if(userEmail != null ){
            UserDetails userDetails =this.appUserRepository.findByEmail(userEmail).orElseThrow();
            if(jwtService.isTokenValid(refreshToken,userDetails)){
               var accessToken =jwtService.generateToken(userDetails);
               // todo save user in data base

               var authResponse = AppUserResponseDto.builder()
                       .accessToken(accessToken)
                       .refreshToken(refreshToken)
                       .build();
               new ObjectMapper().writeValue(response.getOutputStream(),authResponse);
                System.out.println("refresh token est mis a jour");
               return authResponse;
            }
        }
        System.out.println("refresh null ");
       return null;
    }

    public  AppUserResponseDto validUser(AppUserRequestDto appUserRequestDto)
            throws AppUserException {
        AppUser appUser=appUserRepository.findByEmail(appUserRequestDto.getEmail())
                .orElseThrow(() -> new AppUserException(
                        "User With id [%s] not ".formatted(appUserRequestDto.getEmail())));
        appUser.setPassword(passwordEncoder.encode(appUserRequestDto.getPassWord()));

        AppRole appRole=appRoleRepository.findByRoleName("ADMIN").orElseThrow(null);

        Collection<AppRole> appRoles=new ArrayList<>();
        appRoles.add(appRole);
        appUser.setAppRoles(appRoles);

        AppUser saveUser = appUserRepository.save(appUser);
        AppUserResponseDto response=AppUserResponseDto.builder().build();
        BeanUtils.copyProperties(saveUser,response);
        return response;

    }
    public  AppUserResponseDto sendUuidToUser(AppUserRequestDto appUserRequestDto)
            throws AppUserException, ConnectException {
        AppUser appUser=appUserRepository.findByEmail(appUserRequestDto.getEmail())
                .orElseThrow(() -> new AppUserException(
                        "User With id [%s] not ".formatted(appUserRequestDto.getEmail())));

        LocalDateTime expirationDateTime = LocalDateTime.now().plusMinutes(3);
        Date expirationDate = Date.from(expirationDateTime.atZone(ZoneId.systemDefault()).toInstant());
        String uuid = RandomStringUtils.randomAlphanumeric(8);
        appUser.setUuid(uuid);
        appUser.setUuidExpiredDate(expirationDate);
        System.out.println(" uuid Generer :"+uuid);
        try{
            MailStructure mailStructure = mailService.confirmedEmail(appUserRequestDto.getEmail(),uuid);
           // mailService.sendMail(mailStructure);
        }
        catch (Exception c){
            throw  new ConnectException("Verifier votre email ou connection internet: ");
        }
        AppUser saveUser = appUserRepository.save(appUser);
        AppUserResponseDto response=AppUserResponseDto.builder().build();
        BeanUtils.copyProperties(saveUser,response);
        return response;

    }
    public  AppUserResponseDto changePassWord(AppUserRequestDto appUserRequestDto)
            throws AppUserException {
        AppUser appUser=appUserRepository.findByEmail(appUserRequestDto.getEmail())
                .orElseThrow(() -> new AppUserException(
                        "User With id [%s] not ".formatted(appUserRequestDto.getEmail())));
        if( appUserRequestDto.getUuid() == null ){
            System.out.println("===============1");
            //if(!appUserRequestDto.getPassWord().equals(passwordEncoder.encode(appUser.getPassword()))){
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        appUserRequestDto.getEmail(),
                        appUserRequestDto.getPassWord()
                ));
               //: throw new AppUserException(" Mot de passe Entrer n\'est pas de l\'utilisateur [%s]".formatted(appUserRequestDto.getEmail()));
            //}
            appUser.setPassword(passwordEncoder.encode(appUserRequestDto.getNewPassWord()));
        }
        else{
            System.out.println("===============2");
            if( !appUser.getUuid().equals(appUserRequestDto.getUuid()) ){
                throw new AppUserException(" UUID n'est pas valider pour l\'utilisateur [%s]".formatted(appUserRequestDto.getEmail()));
            }
            if( appUser.getUuidExpiredDate().before(new Date()) ){
                throw new AppUserException(" Code de validation est éxpirer ! ");
            }
            appUser.setPassword(passwordEncoder.encode(appUserRequestDto.getPassWord()));
        }

        appUser.setUuid(null);
        appUser.setUuidExpiredDate(null);
        AppUser saveUser = appUserRepository.save(appUser);
        AppUserResponseDto response=AppUserResponseDto.builder().build();
        BeanUtils.copyProperties(saveUser,response);
        System.out.println("hali");
        return response;

    }

}
