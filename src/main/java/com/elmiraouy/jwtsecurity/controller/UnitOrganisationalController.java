package com.elmiraouy.jwtsecurity.controller;

import com.elmiraouy.jwtsecurity.Dto.request.AddressUnityRequestDto;
import com.elmiraouy.jwtsecurity.Dto.request.UnitOrganisationalRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.AddressUnityResponseDto;
import com.elmiraouy.jwtsecurity.Dto.response. UnitOrganisationalResponseDto;
import com.elmiraouy.jwtsecurity.entities. UnitOrganisational;
import com.elmiraouy.jwtsecurity.handlerException.*;
import com.elmiraouy.jwtsecurity.service.AddressUnityService;
import com.elmiraouy.jwtsecurity.service. UnitOrganisationalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client/unities")
public class UnitOrganisationalController {
    private final  UnitOrganisationalService  unitOrganisationalService;
    private final AddressUnityService addressUnityService;

    @PostMapping("/duplicate")
    public  ResponseEntity<UnitOrganisationalResponseDto> duplicate(@RequestBody UnitOrganisationalRequestDto unitOrganisationalRequestDto) throws CompanyException, AppUserException, UnityException {
        return ResponseEntity.ok(unitOrganisationalService.duplicate(unitOrganisationalRequestDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    }

    @GetMapping
    public ResponseEntity<List<UnitOrganisationalResponseDto>> getByCompanyId(@RequestParam Long companyId) throws EntityNotFoundException {
        System.out.println("id recu est :"+companyId);
        return ResponseEntity.ok( unitOrganisationalService.getByCompanyId(companyId));
    }

    @PostMapping("/address")
    public  ResponseEntity<AddressUnityResponseDto> addAddressToUnity(@RequestBody AddressUnityRequestDto addressUnityRequestDto,@RequestParam(required = true) boolean newAddress) throws CompanyException, AppUserException, UnityException, EntityNotFoundException, AddressUnityException {
        return ResponseEntity.ok(addressUnityService.update(addressUnityRequestDto,newAddress));
    }
}
