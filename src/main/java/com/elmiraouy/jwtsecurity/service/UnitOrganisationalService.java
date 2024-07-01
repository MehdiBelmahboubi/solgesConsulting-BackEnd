package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.UnitOrganisationalRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response. UnitOrganisationalResponseDto;
import com.elmiraouy.jwtsecurity.entities. UnitOrganisational;
import com.elmiraouy.jwtsecurity.handlerException.AppUserException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.EntityNotFoundException;
import com.elmiraouy.jwtsecurity.handlerException.UnityException;

import java.util.List;

public interface UnitOrganisationalService {

    public  UnitOrganisationalResponseDto findById(Long id) throws EntityNotFoundException;
    public List<UnitOrganisationalResponseDto> getAll() throws EntityNotFoundException;
    public  UnitOrganisationalResponseDto findByNameOrCode(String criteria) throws EntityNotFoundException;
    public  UnitOrganisational findInternById(Long id) throws UnityException;

}
