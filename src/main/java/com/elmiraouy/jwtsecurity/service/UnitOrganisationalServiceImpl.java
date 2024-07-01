package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.UnitOrganisationalRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response. UnitOrganisationalResponseDto;
import com.elmiraouy.jwtsecurity.entities.AppUser;
import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.entities.TypeUnitOrganisational;
import com.elmiraouy.jwtsecurity.entities. UnitOrganisational;
import com.elmiraouy.jwtsecurity.handlerException.AppUserException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.EntityNotFoundException;
import com.elmiraouy.jwtsecurity.handlerException.UnityException;
import com.elmiraouy.jwtsecurity.mappers. UnitOrganisationalDtoMapper;
import com.elmiraouy.jwtsecurity.repository.AppUserRepository;
import com.elmiraouy.jwtsecurity.repository.CompanyRepository;
import com.elmiraouy.jwtsecurity.repository.TypeUnitOrganisationalRepository;
import com.elmiraouy.jwtsecurity.repository. UnitOrganisationalRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UnitOrganisationalServiceImpl implements  UnitOrganisationalService{

    private final UnitOrganisationalRepository  unitOrganisationalRepository;
    private final UnitOrganisationalDtoMapper  unitOrganisationalDtoMapper;
    private final CompanyRepository companyRepository;
    private final AppUserRepository userRepository;
    private final TypeUnitOrganisationalRepository typeUnitOrganisationalRepository;



    @Override
    public  UnitOrganisationalResponseDto findById(Long id) throws EntityNotFoundException {
        return null;
    }
    @Override
    public  UnitOrganisational findInternById(Long id) throws UnityException {
        return unitOrganisationalRepository.findById(id).orElseThrow(
                () -> new UnityException(
                    "Unity With id [%s] not Exist".formatted(id)));
    }

    


    @Override
    public List<UnitOrganisationalResponseDto> getAll() throws EntityNotFoundException {
        List< UnitOrganisational>  UnitOrganisationalList= unitOrganisationalRepository.findAll();
        return  UnitOrganisationalList.stream().map( unitOrganisationalDtoMapper).toList();
    }



    @Override
    public  UnitOrganisationalResponseDto findByNameOrCode(String criteria) throws EntityNotFoundException {
         unitOrganisationalRepository.findByNameOrCode(criteria);
        return null;
    }
}
