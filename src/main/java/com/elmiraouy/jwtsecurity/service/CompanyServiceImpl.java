package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.CompanyRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.AppUserResponseDto;
import com.elmiraouy.jwtsecurity.Dto.response.CompanyResponseDto;
import com.elmiraouy.jwtsecurity.entities.AppUser;
import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.entities.Image;
import com.elmiraouy.jwtsecurity.handlerException.AppUserException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.EntityNotFoundException;
import com.elmiraouy.jwtsecurity.mappers.CompanyDtoMapper;
import com.elmiraouy.jwtsecurity.repository.AppUserRepository;
import com.elmiraouy.jwtsecurity.repository.CompanyRepository;
import com.elmiraouy.jwtsecurity.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyDtoMapper companyDtoMapper;
    private final AppUserRepository userRepository;
    private final ImageRepository imageRepository;
    @Override
    public List<CompanyResponseDto> findAll() {
        List<Company> companies=companyRepository.findAll();
        return companies.stream().map(companyDtoMapper).toList();
    }

    @Override
    public CompanyResponseDto findById(Long id) throws CompanyException {
        Company company=companyRepository.findById(id)
                .orElseThrow(() -> new CompanyException(
                        "Company With id [%s] not Exist".formatted(id)));
       return companyDtoMapper.apply(company);
    }
    @Override
    public CompanyResponseDto create(CompanyRequestDto companyRequestDto) {
        Company company=companyDtoMapper.dtoToCompany(companyRequestDto);
        company.setDateCreation(LocalDateTime.now());
        company.setDateUpdate(null);

        return null;
    }

    @Override
    public CompanyResponseDto delete(Long id) {
        return null;
    }

    @Override
    public CompanyResponseDto update(Long id, CompanyRequestDto companyRequestDto) throws CompanyException {
        Company company=companyRepository.findById(id)
                .orElseThrow(() -> new CompanyException(
                        "Company With id [%s] not ".formatted(id)));
        return null;
    }

    @Override
    public void addImageToCompany(Long idImage, Long idCompany) throws EntityNotFoundException, CompanyException {

        Image image =imageRepository.findById(idImage)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Image With id [%s] not ".formatted(idImage)));
        Company company=companyRepository.findById(idCompany)
                .orElseThrow(() -> new CompanyException(
                        "Company With id [%s] not ".formatted(idCompany)));
        company.setImage(image);
        companyRepository.save(company);
    }

    @Override
    public void addCompanyToUser(Long idUser, Long idCompany)
            throws AppUserException, CompanyException {
        AppUser appUser=userRepository.findAppUserById(idUser)
                .orElseThrow(() -> new AppUserException(
                        "User With id [%s] not Exist".formatted(idUser)));
        Company company=companyRepository.findById(idCompany)
                .orElseThrow(() -> new CompanyException(
                        "Company With id [%s] not Exist".formatted(idCompany)));
    appUser.getAdminOnCompanies().add(company);
    appUser.getCompanies().add(company);
    company.getUsers().add(appUser);
    company.setAdmin(appUser);
    userRepository.save(appUser);
    companyRepository.save(company);

    }


}
