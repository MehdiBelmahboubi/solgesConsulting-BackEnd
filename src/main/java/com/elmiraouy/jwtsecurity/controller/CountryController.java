package com.elmiraouy.jwtsecurity.controller;

import com.elmiraouy.jwtsecurity.Dto.response.CountryResponseDto;
import com.elmiraouy.jwtsecurity.entities.Country;
import com.elmiraouy.jwtsecurity.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/client/country")
public class CountryController {
    private final CountryService countryService;

    @GetMapping("/getAllNationalities")
    public ResponseEntity<List<CountryResponseDto>> getAllNationalities(){
        return ResponseEntity.ok(countryService.getAllNationalities());
    }
}
