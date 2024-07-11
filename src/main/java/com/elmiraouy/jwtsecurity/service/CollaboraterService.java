package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.CollaboraterRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto;
import com.elmiraouy.jwtsecurity.entities.Collaborater;
import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.handlerException.CollaboraterException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.CountryException;

import java.util.List;

public interface CollaboraterService {
    public List<CollaboraterResponseDto> findByCompany(Long companyId) throws CompanyException;
    public CollaboraterResponseDto createCollab(CollaboraterRequestDto CollaboraterRequestDto) throws CollaboraterException, CompanyException, CountryException;
    public Collaborater buildCollaborater(CollaboraterRequestDto request, Company company);
    public void addNationalitiesToCollaborater(Collaborater collaborater, Long countryCode1, Long countryCode2) throws CountryException;
    public CollaboraterResponseDto deleteCollab(Long id);
    public CollaboraterResponseDto updateCollab(Long id , CollaboraterRequestDto CollaboraterRequestDto);
}
