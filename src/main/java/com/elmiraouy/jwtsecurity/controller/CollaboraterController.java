package com.elmiraouy.jwtsecurity.controller;

import com.elmiraouy.jwtsecurity.Dto.request.CollaboraterRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.*;
import com.elmiraouy.jwtsecurity.service.CollaboraterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/client/collaborators")
public class CollaboraterController {

    private final CollaboraterService collaboraterService;

    @GetMapping
    public ResponseEntity<Page<CollaboraterResponseDto>> getAll(
            @RequestParam Long id,
            @RequestParam Boolean active,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String selectedType,
            @RequestParam(required = false) String selectedOption,
            @RequestParam(required = false) String search) throws CompanyException {

        if (selectedType!=null) {
            return ResponseEntity.ok(collaboraterService.findByCompanyGroupedBy(id, active, page, size,selectedType,selectedOption));
        } else if (search!=null) {
            return ResponseEntity.ok(collaboraterService.findByCompanyAndSearch(id, active, page, size, search));
        }


        return ResponseEntity.ok(collaboraterService.findByCompany(id, active, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollaboraterResponseDto> getById(@PathVariable Long id) throws CollaboraterException, ContractException , ClassificationException {
        return ResponseEntity.ok(collaboraterService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CollaboraterResponseDto> createCollab(@RequestBody CollaboraterRequestDto collaboraterRequestDto) throws CollaboraterException, CompanyException, CountryException {
        return ResponseEntity.ok(collaboraterService.createCollab(collaboraterRequestDto));
    }

    @PutMapping
    public ResponseEntity<CollaboraterResponseDto> updateCollab(@RequestBody CollaboraterRequestDto collaboraterRequestDto) throws CollaboraterException {
        return ResponseEntity.ok(collaboraterService.updateCollab(collaboraterRequestDto));
    }

    @DeleteMapping
    public ResponseEntity<CollaboraterResponseDto> deleteCollab(@RequestParam Long id) throws CollaboraterException {
        return ResponseEntity.ok(collaboraterService.deleteCollab(id));
    }

    @DeleteMapping("/restore")
    public ResponseEntity<CollaboraterResponseDto> restoreCollab(@RequestParam Long id) throws CollaboraterException {
        return ResponseEntity.ok(collaboraterService.restoreCollab(id));
    }
}
