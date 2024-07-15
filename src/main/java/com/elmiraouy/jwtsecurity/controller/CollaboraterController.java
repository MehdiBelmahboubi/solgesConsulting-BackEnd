package com.elmiraouy.jwtsecurity.controller;

import com.elmiraouy.jwtsecurity.Dto.request.CollaboraterRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.CollaboraterException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.CountryException;
import com.elmiraouy.jwtsecurity.service.CollaboraterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/client/collaborater")
public class CollaboraterController {

    private final CollaboraterService collaboraterService;

    @GetMapping("/company/{id}/collaboraters")
    public ResponseEntity<List<CollaboraterResponseDto>> getAll(@PathVariable Long id) throws  CompanyException {
        return ResponseEntity.ok(collaboraterService.findByCompany(id));
    }

    @GetMapping("/get")
    public ResponseEntity<CollaboraterResponseDto> getById(@RequestParam Long id) throws CollaboraterException {
        return ResponseEntity.ok(collaboraterService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<CollaboraterResponseDto> createCollab(@RequestBody CollaboraterRequestDto collaboraterRequestDto) throws CollaboraterException, CompanyException, CountryException {
        return ResponseEntity.ok(collaboraterService.createCollab(collaboraterRequestDto));
    }
}
