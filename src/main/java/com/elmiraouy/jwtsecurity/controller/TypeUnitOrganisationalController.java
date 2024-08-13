package com.elmiraouy.jwtsecurity.controller;

import com.elmiraouy.jwtsecurity.Dto.request.TypeUnitOrganisationalRequest;
import com.elmiraouy.jwtsecurity.Dto.response.TypeUnitOrganisationalResponseDto;
import com.elmiraouy.jwtsecurity.entities.TypeUnitOrganisational;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.EntityNotFoundException;
import com.elmiraouy.jwtsecurity.handlerException.TypeUnityException;
import com.elmiraouy.jwtsecurity.service.TypeUnitOrganisationalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client/unities/type")
public class TypeUnitOrganisationalController  {
    private final TypeUnitOrganisationalService typeUnitOrganisationalService;


    @DeleteMapping
    public void delete(Long id) {

    }
    @GetMapping("/{id}")
    public TypeUnitOrganisationalResponseDto findById(@RequestParam Long id) throws EntityNotFoundException {
        return null;
    }

    @GetMapping("/{criteria}")
    public TypeUnitOrganisationalResponseDto findByNameOrCode(String criteria) throws EntityNotFoundException {
        return null;
    }
}