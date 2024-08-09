package com.elmiraouy.jwtsecurity.controller;

import com.elmiraouy.jwtsecurity.Dto.request.JourFerierRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.JourFerierResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.FeteException;
import com.elmiraouy.jwtsecurity.handlerException.JourFerierException;
import com.elmiraouy.jwtsecurity.handlerException.TypeFeteException;
import com.elmiraouy.jwtsecurity.service.JourFerierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/client/jourferier")
public class JourFerierController {
    private final JourFerierService jourFerierService;

    @GetMapping
    public ResponseEntity<List<JourFerierResponseDto>> getAll(@RequestParam Long id){
        return ResponseEntity.ok(jourFerierService.getAll(id));
    }

    @PostMapping
    public ResponseEntity<JourFerierResponseDto> addJourFerier(@RequestBody JourFerierRequestDto jourFerierRequestDto) throws TypeFeteException, FeteException, JourFerierException {
        return ResponseEntity.ok(jourFerierService.addJourFerier(jourFerierRequestDto));
    }
}
