package com.elmiraouy.jwtsecurity.controller;

import com.elmiraouy.jwtsecurity.Dto.request.UnitOrganisationalRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response. UnitOrganisationalResponseDto;
import com.elmiraouy.jwtsecurity.entities. UnitOrganisational;
import com.elmiraouy.jwtsecurity.handlerException.AppUserException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.EntityNotFoundException;
import com.elmiraouy.jwtsecurity.handlerException.UnityException;
import com.elmiraouy.jwtsecurity.service. UnitOrganisationalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client/unities")
public class UnitOrganisationalController {
    private final  UnitOrganisationalService  unitOrganisationalService;

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

    }
    @GetMapping("/{id}")
    public  UnitOrganisationalResponseDto findById(@PathVariable Long id) throws EntityNotFoundException {
        return null;
    }


    @GetMapping("/{criteria}")
    public  UnitOrganisationalResponseDto findByNameOrCode(String criteria) throws EntityNotFoundException {
        return null;
    }
}
