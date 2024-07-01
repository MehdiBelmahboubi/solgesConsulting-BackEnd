package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.TypeUnitOrganisationalRequest;
import com.elmiraouy.jwtsecurity.Dto.response.TypeUnitOrganisationalResponseDto;
import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.entities.TypeUnitOrganisational;
import com.elmiraouy.jwtsecurity.entities.UnitOrganisational;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.EntityNotFoundException;
import com.elmiraouy.jwtsecurity.handlerException.TypeUnityException;
import com.elmiraouy.jwtsecurity.mappers.TypeUnitOrganisationalDtoMapper;
import com.elmiraouy.jwtsecurity.repository.CompanyRepository;
import com.elmiraouy.jwtsecurity.repository.TypeUnitOrganisationalRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class TypeUnitOrganisationalServiceImpl implements TypeUnitOrganisationalService{

    private final TypeUnitOrganisationalRepository typeUnitOrganisationalRepository;
    private final TypeUnitOrganisationalDtoMapper typeUnitOrganisationalDtoMapper;
    private final CompanyRepository companyRepository;

    @Transactional
        @Override
    public TypeUnitOrganisationalResponseDto findById(Long id) throws EntityNotFoundException {
        return null;
    }
    @Transactional
    @Override
    public List<TypeUnitOrganisationalResponseDto> getAll(Long companyId) throws EntityNotFoundException {
        List<TypeUnitOrganisationalResponseDto> typeUnitOrganisationalList=typeUnitOrganisationalRepository.findByCompanyId(companyId);
        if(typeUnitOrganisationalList.isEmpty()) {
            return new ArrayList<>();
        }
        return typeUnitOrganisationalList;
    }

    @Override
    public TypeUnitOrganisationalResponseDto findByNameOrCode(String criteria) throws EntityNotFoundException {
        return null;
    }

    @Override
    public List<TypeUnitOrganisationalResponseDto> findByCompanyId(Long companyId) throws EntityNotFoundException {
          List<TypeUnitOrganisational> typeUnitOrganisationalList=typeUnitOrganisationalRepository.findAll();
          return typeUnitOrganisationalList.stream().map(typeUnitOrganisationalDtoMapper).toList();
    }
}
