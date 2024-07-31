package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.TypeUnitOrganisationalRequest;
import com.elmiraouy.jwtsecurity.Dto.response.TypeUnitOrganisationalResponseDto;
import com.elmiraouy.jwtsecurity.entities.TypeUnitOrganisational;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.EntityNotFoundException;
import com.elmiraouy.jwtsecurity.handlerException.TypeUnityException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface TypeUnitOrganisationalService {

    public TypeUnitOrganisationalResponseDto findById(Long id) throws EntityNotFoundException;
    public List<TypeUnitOrganisationalResponseDto> getAll(Long companyId) throws EntityNotFoundException;
    public TypeUnitOrganisationalResponseDto findByNameOrCode(String criteria) throws EntityNotFoundException;
    public List<TypeUnitOrganisationalResponseDto> findByCompanyId(Long companyId) throws EntityNotFoundException;
    void persistFromFile(MultipartFile file, String table, Long companyId, Long userCreatedId);
}
