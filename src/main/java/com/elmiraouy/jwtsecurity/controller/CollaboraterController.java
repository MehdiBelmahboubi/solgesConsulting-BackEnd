package com.elmiraouy.jwtsecurity.controller;

import com.elmiraouy.jwtsecurity.Dto.request.CollaboraterRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.CollaboraterException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.ContractException;
import com.elmiraouy.jwtsecurity.handlerException.CountryException;
import com.elmiraouy.jwtsecurity.service.CollaboraterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/client/collaborater")
public class CollaboraterController {

    private final CollaboraterService collaboraterService;

    @GetMapping("/getAll")
    public ResponseEntity<Page<CollaboraterResponseDto>> getAll(
            @RequestParam Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) throws CompanyException {
        return ResponseEntity.ok(collaboraterService.findByCompany(id, page, size));
    }

    @GetMapping("/getArchived")
    public ResponseEntity<Page<CollaboraterResponseDto>> getArchived(
            @RequestParam Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) throws CompanyException {
        return ResponseEntity.ok(collaboraterService.findArchivedByCompany(id, page, size));
    }

    @GetMapping("/get")
    public ResponseEntity<CollaboraterResponseDto> getById(@RequestParam Long id) throws CollaboraterException, ContractException {
        return ResponseEntity.ok(collaboraterService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<CollaboraterResponseDto> createCollab(@RequestBody CollaboraterRequestDto collaboraterRequestDto) throws CollaboraterException, CompanyException, CountryException {
        return ResponseEntity.ok(collaboraterService.createCollab(collaboraterRequestDto));
    }

    @PutMapping("/update")
    public ResponseEntity<CollaboraterResponseDto> updateCollab(@RequestBody CollaboraterRequestDto collaboraterRequestDto) throws CollaboraterException {
        return ResponseEntity.ok(collaboraterService.updateCollab(collaboraterRequestDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CollaboraterResponseDto> deleteCollab(@RequestParam Long id) throws CollaboraterException {
        return ResponseEntity.ok(collaboraterService.deleteCollab(id));
    }
}
