package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.AppUserRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.AppUserResponseDto;
import com.elmiraouy.jwtsecurity.entities.*;
import com.elmiraouy.jwtsecurity.handlerException.AppUserException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.EntityNotFoundException;
import com.elmiraouy.jwtsecurity.mappers.AppUserDtoMapper;
import com.elmiraouy.jwtsecurity.repository.AppRoleRepository;
import com.elmiraouy.jwtsecurity.repository.AppUserRepository;
import com.elmiraouy.jwtsecurity.repository.CompanyRepository;
import com.elmiraouy.jwtsecurity.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserDtoMapper appUserDtoMapper;
    private final ImageService imageService;
    private final ImageRepository imageRepository;
    private final CompanyService companyService;
    private final CompanyRepository companyRepository;
    private final AppRoleRepository appRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<AppUserResponseDto> findAll() {
        List<AppUser> users=appUserRepository.findAll();
        return users.stream().map(appUserDtoMapper).toList();

    }

    @Transactional
    @Override
    public AppUserResponseDto findByEmail(String email) throws AppUserException {
        AppUser user=appUserRepository.findByEmail(email)
                .orElseThrow(() -> new AppUserException(
                        "User With id [%s] not ".formatted(email)));
        return appUserDtoMapper.appUserToDto(user);
    }
    @Override
    public AppUserResponseDto findById(Long id) throws AppUserException {
        AppUser appUser=appUserRepository.findAppUserById(id)
                .orElseThrow(() -> new AppUserException(
                        "User With id [%s] not ".formatted(id)));
       return appUserDtoMapper.appUserToDto(appUser);
    }

    @Override
    public AppUserResponseDto delete(Long id) {
        return null;
    }

    @Override
    public AppUserResponseDto update(Long id, AppUserRequestDto userRequestDto) throws AppUserException {
        AppUser user=appUserRepository.findById(id)
                .orElseThrow(() -> new AppUserException(
                        "User With id [%s] not ".formatted(id)));
        return null;
    }

    @Override
    public void addTokenToUser(AppUser appUser, Token token) throws AppUserException {

        AppUser appUser1=appUserRepository.findByEmail(appUser.getEmail()).orElseThrow(() -> new AppUserException(
                "User With Email: [%s] not ".formatted(appUser.getEmail())));
        appUser1.setToken(token);
        appUserRepository.save(appUser1);
    }

    @Override
    public AppUserResponseDto register(AppUserRequestDto appUserRequestDto)
            throws EntityNotFoundException, AppUserException, CompanyException {
        AppUser user=appUserRepository.findByEmail(appUserRequestDto.getEmail())
                .orElseThrow(() -> new AppUserException(
                        "User With id [%s] not Exist".formatted(appUserRequestDto.getEmail())));
        user.setPassword(passwordEncoder.encode(appUserRequestDto.getPassWord()));
        AppRole appRoleClient=appRoleRepository.findByRoleName("client").orElseThrow(
                ()-> new EntityNotFoundException("role width this [%s] not Exist".formatted("client"))
        );
        AppRole appRoleAdmin=appRoleRepository.findByRoleName("manager").orElseThrow(
                ()-> new EntityNotFoundException("role width this [%s] not Exist".formatted("manager"))
        );
        user.getAppRoles().add(appRoleAdmin);
        user.getAppRoles().add(appRoleClient);

        Company company =new Company();
        company.setAdmin(user);
        company.setImage(appUserRequestDto.getCompany().getImage());
        company.setName(appUserRequestDto.getCompany().getName());
        company.setCode(appUserRequestDto.getCompany().getCode());
        company.setAddress(appUserRequestDto.getCompany().getAddress());
        company.setDateUpdate(null);
        company.setDateCreation(LocalDateTime.now());
        // todo associate image to Company && user
        Image imageCompany=imageService.findById(appUserRequestDto.getCompany().getImage().getId());
        company.setImage(imageCompany);
        Company companySave = companyRepository.save(company);

        user.getCompanies().add(companySave);
        user.setImage(imageCompany);
        user.setDateCreation(LocalDateTime.now());
        // todo associate image to Company && user
        //Image imageCompany=imageService.findById(appUserRequestDto.getCompany().getImage().getId());
        //company.setImage(imageCompany);

        companyService.addImageToCompany(imageCompany.getId(),companySave.getId());
        // todo associate Company to user
        companyService.addCompanyToUser(user.getId(), companySave.getId());
        user.setToken(null);
        AppUser saveUser = appUserRepository.save(user);
        return appUserDtoMapper.apply(saveUser);
    }
    @Override
    public void addImageToUser(Long idImage, Long idUser) throws EntityNotFoundException, CompanyException {

        Image image =imageRepository.findById(idImage)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Image With id [%s] not ".formatted(idImage)));
        AppUser appUser=appUserRepository.findAppUserById(idUser)
                .orElseThrow(() -> new CompanyException(
                        "Company With id [%s] not ".formatted(idUser)));
        appUser.setImage(image);
        appUserRepository.save(appUser);
    }
    @Override
    public  AppUserResponseDto confirmedEmail(AppUserRequestDto appUserRequestDto) throws AppUserException {
        AppUser appUser=appUserRepository.findByEmail(appUserRequestDto.getEmail())
                .orElseThrow(() -> new AppUserException(
                        "User With id [%s] not Exist ".formatted(appUserRequestDto.getEmail())));

        if(!appUser.getUuid().equals(appUserRequestDto.getUuid())){
            throw new AppUserException(" UUID not valid for User [%s]".formatted(appUserRequestDto.getEmail()));
        }
        if(appUser.getUuidExpiredDate().before(new Date())){
            appUser.setUuid(null);
            appUser.setUuidExpiredDate(null);
            appUserRepository.delete(appUser);
            throw new AppUserException(" Validation code expired  !");
        }
        AppUser saveUser = appUserRepository.save(appUser);
        AppUserResponseDto response=AppUserResponseDto.builder().build();
        BeanUtils.copyProperties(saveUser,response);
        return response;
    }
    @Override
    @Transactional
    public void addRoleToUser(Long idRole, Long idUser) throws EntityNotFoundException, CompanyException {
        AppRole role = appRoleRepository.findById(idRole)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Role With id [%s] not found".formatted(idRole)));

        AppUser appUser = appUserRepository.findById(idUser)
                .orElseThrow(() -> new CompanyException(
                        "User With id [%s] not found".formatted(idUser)));

        role.getUsers().add(appUser);
        appUser.getAppRoles().add(role);

        appRoleRepository.save(role);
        appUserRepository.save(appUser);

        System.out.println(appUser);
    }

}
