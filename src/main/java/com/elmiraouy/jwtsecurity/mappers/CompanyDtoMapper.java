package com.elmiraouy.jwtsecurity.mappers;

import com.elmiraouy.jwtsecurity.Dto.request.CompanyRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CompanyResponseDto;
import com.elmiraouy.jwtsecurity.entities.Company;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CompanyDtoMapper implements Function<Company, CompanyResponseDto> {

    @Override
    public CompanyResponseDto apply(Company company) {
        return new CompanyResponseDto(
                company.getId(),
                company.getName(),
                company.getRaisonSocial(),
                company.getCode(),
                company.getTitledCommercial(),
                company.getRc(),
                company.getIce(),
                company.getIss(),
                company.getTva(),
                company.getPatent(),
                company.getCnss(),
                company.getCodePostal(),
                company.getUsers(),
                company.getFilialCompany(),
                company.getParentCompany(),
                company.getUnitOrganisationalList()
        );
    }
    public CompanyResponseDto companyToDto(Company company){
            return new CompanyResponseDto(
                    company.getId(),
                    company.getName(),
                    company.getRaisonSocial(),
                    company.getCode(),
                    company.getTitledCommercial(),
                    company.getRc(),
                    company.getIce(),
                    company.getIss(),
                    company.getTva(),
                    company.getCnss(),
                    company.getCodePostal()
            );
    };
    public Company dtoToCompany(CompanyRequestDto companyRequestDto){
        return new Company(
                companyRequestDto.getId(),
                companyRequestDto.getName(),
                companyRequestDto.getRaisonSocial(),
                companyRequestDto.getCode(),
                companyRequestDto.getTitledCommercial(),
                companyRequestDto.getRc(),
                companyRequestDto.getIce(),
                companyRequestDto.getIss(),
                companyRequestDto.getTva(),
                companyRequestDto.getPatent(),
                companyRequestDto.getCnss(),
                companyRequestDto.getCodePostal(),
                companyRequestDto.number
        );
    }
}
