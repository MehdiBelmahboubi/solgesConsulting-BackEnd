package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.CollaboraterRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto;
import com.elmiraouy.jwtsecurity.entities.Collaborater;
import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.handlerException.*;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CollaboraterService {
    public Page<CollaboraterResponseDto> findByCompany(Long companyId,Boolean active, int pageable, int size) throws CompanyException;
    public CollaboraterResponseDto findById(Long id) throws CollaboraterException, ContractException, ClassificationException;
    public CollaboraterResponseDto createCollab(CollaboraterRequestDto CollaboraterRequestDto) throws CollaboraterException, CompanyException, CountryException;
    public Collaborater buildCollaborater(CollaboraterRequestDto request) throws CollaboraterException, CompanyException;
    public void addNationalitiesToCollaborater(Collaborater collaborater, Long countryCode1, Long countryCode2) throws CountryException;
    public CollaboraterResponseDto deleteCollab(Long id) throws CollaboraterException;
    public CollaboraterResponseDto restoreCollab(Long id) throws CollaboraterException;
    public CollaboraterResponseDto updateCollab(CollaboraterRequestDto CollaboraterRequestDto) throws CollaboraterException;

    void persistFromFile(MultipartFile file, String table, Long companyId);

    Page<CollaboraterResponseDto> findByCompanyGroupedBy(Long companyId, Boolean active, int page, int size ,String selectedType,String selectedOption);

    Page<CollaboraterResponseDto> findByCompanyAndSearch(Long companyId, Boolean active, int page, int size, String search);

}
