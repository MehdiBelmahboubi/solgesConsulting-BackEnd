package com.elmiraouy.jwtsecurity.controller;

import com.elmiraouy.jwtsecurity.Dto.request.JourFerierRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.JourFerierResponseDto;
import com.elmiraouy.jwtsecurity.service.JourFerierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/client/jourferier")
public class JourFerierController {
    private final JourFerierService jourFerierService;

    @PostMapping
    public ResponseEntity<JourFerierResponseDto> addJourFerier(@RequestBody JourFerierRequestDto jourFerierRequestDto){
        return ResponseEntity.ok(jourFerierService.addJourFerier(jourFerierRequestDto));
    }
}
