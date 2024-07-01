package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.CompanyRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CompanyResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.AppUserException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.EntityNotFoundException;

import java.util.List;

public interface CompanyService {
    public List<CompanyResponseDto> findAll();
    public CompanyResponseDto findById(Long id) throws CompanyException;
    public CompanyResponseDto create(CompanyRequestDto companyRequestDto);
    public CompanyResponseDto delete(Long id);
    public CompanyResponseDto update(Long id , CompanyRequestDto companyRequestDto) throws CompanyException;
    public void addImageToCompany(Long idImage,Long IdCompany) throws EntityNotFoundException, CompanyException;
    public void addCompanyToUser(Long idUser,Long IdCompany) throws AppUserException, CompanyException;
}
