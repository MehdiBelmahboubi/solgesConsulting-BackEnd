package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.CollaboraterRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto;
import com.elmiraouy.jwtsecurity.entities.Collaborater;
import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.handlerException.CollaboraterException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.ContractException;
import com.elmiraouy.jwtsecurity.handlerException.CountryException;

import java.util.List;

public interface CollaboraterService {
    public List<CollaboraterResponseDto> findByCompany(Long companyId) throws CompanyException;
    public CollaboraterResponseDto findById(Long id) throws CollaboraterException, ContractException;
    public CollaboraterResponseDto createCollab(CollaboraterRequestDto CollaboraterRequestDto) throws CollaboraterException, CompanyException, CountryException;
    public Collaborater buildCollaborater(CollaboraterRequestDto request, Company company) throws CollaboraterException;
    public void addNationalitiesToCollaborater(Collaborater collaborater, Long countryCode1, Long countryCode2) throws CountryException;
    public CollaboraterResponseDto deleteCollab(Long id);
    public CollaboraterResponseDto updateCollab(CollaboraterRequestDto CollaboraterRequestDto) throws CollaboraterException;
}
