package com.elmiraouy.jwtsecurity.controller;

import com.elmiraouy.jwtsecurity.Dto.request.TypeUnitOrganisationalRequest;
import com.elmiraouy.jwtsecurity.Dto.response.ResponseDto;
import com.elmiraouy.jwtsecurity.Dto.response.TypeUnitOrganisationalResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.EntityNotFoundException;
import com.elmiraouy.jwtsecurity.handlerException.TypeUnityException;
import com.elmiraouy.jwtsecurity.service.TypeUnitOrganisationalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client/unities/type")
public class TypeUnitOrganisationalController  {
    private final TypeUnitOrganisationalService typeUnitOrganisationalService;

   @PostMapping
    public ResponseEntity<TypeUnitOrganisationalResponseDto> save(@RequestBody  TypeUnitOrganisationalRequest typeUnitOrganisationalRequestl) throws EntityNotFoundException, CompanyException, TypeUnityException {
       return ResponseEntity.ok(typeUnitOrganisationalService.save(typeUnitOrganisationalRequestl));
    }
    @PutMapping
    public ResponseEntity<TypeUnitOrganisationalResponseDto> update(@RequestBody  TypeUnitOrganisationalRequest typeUnitOrganisationalRequestl) throws EntityNotFoundException, CompanyException, TypeUnityException {
       return ResponseEntity.ok(typeUnitOrganisationalService.update(typeUnitOrganisationalRequestl));
    }
    @PostMapping("/duplicate")
    public ResponseEntity<TypeUnitOrganisationalResponseDto> duplicate(@RequestBody  TypeUnitOrganisationalRequest typeUnitOrganisationalRequestl) throws EntityNotFoundException, CompanyException, TypeUnityException {
       return ResponseEntity.ok(typeUnitOrganisationalService.duplicate(typeUnitOrganisationalRequestl));
    }
    @DeleteMapping
    public void delete(Long id) {

    }
    @GetMapping("/{id}")
    public ResponseEntity<TypeUnitOrganisationalResponseDto> findById(@RequestParam Long id) throws EntityNotFoundException {
        return null;
    }
    @GetMapping
    public ResponseEntity<List<TypeUnitOrganisationalResponseDto>> getAll(
            @RequestParam(name = "companyId", required = true) Long companyId
    ) throws EntityNotFoundException {
        return ResponseEntity.ok(typeUnitOrganisationalService.findByCompanyId(companyId));
    }


}
