package com.elmiraouy.jwtsecurity.controller;

import com.elmiraouy.jwtsecurity.Dto.request.FeteRequestDto;
import com.elmiraouy.jwtsecurity.Dto.request.JourFerierRequestDto;
import com.elmiraouy.jwtsecurity.Dto.request.TypeFeteRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CalendarResponseDto;
import com.elmiraouy.jwtsecurity.Dto.response.FeteResponseDto;
import com.elmiraouy.jwtsecurity.Dto.response.JourFerierResponseDto;
import com.elmiraouy.jwtsecurity.Dto.response.TypeFeteResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.*;
import com.elmiraouy.jwtsecurity.service.JourFerierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/client/jourferies")
public class JourFerierController {
    private final JourFerierService jourFerierService;

    @GetMapping
    public ResponseEntity<List<JourFerierResponseDto>> getAll(@RequestParam Long id,@RequestParam Boolean statut){
        return ResponseEntity.ok(jourFerierService.getAll(id,statut));
    }

    @PostMapping
    public ResponseEntity<JourFerierResponseDto> addJourFerier(@RequestBody JourFerierRequestDto jourFerierRequestDto) throws TypeFeteException, FeteException, JourFerierException, CompanyException {
        return ResponseEntity.ok(jourFerierService.addJourFerier(jourFerierRequestDto));
    }

    @PostMapping("/fetes")
    public ResponseEntity<FeteResponseDto> addFete(@RequestBody FeteRequestDto feteRequestDto) throws TypeFeteException, CompanyException {
        return ResponseEntity.ok(jourFerierService.addFete(feteRequestDto));
    }

    @PostMapping("/typesFetes")
    public ResponseEntity<TypeFeteResponseDto> addTypeFete(@RequestBody TypeFeteRequestDto typeFeteRequestDto) throws CompanyException {
        return ResponseEntity.ok(jourFerierService.addTypeFete(typeFeteRequestDto));
    }

    @GetMapping("/fetes")
    public ResponseEntity<List<FeteResponseDto>> getFetes(@RequestParam Long id){
        return ResponseEntity.ok(jourFerierService.getFetes(id));
    }

    @GetMapping("/typesFetes")
    public ResponseEntity<List<TypeFeteResponseDto>> getTypesFetes(@RequestParam Long id){
        return ResponseEntity.ok(jourFerierService.getTypesFetes(id));
    }

    @DeleteMapping
    public ResponseEntity<JourFerierResponseDto> deleteJourFerier(@RequestParam Long id) throws JourFerierException {
        return ResponseEntity.ok(jourFerierService.deleteCalendar(id));
    }

    @DeleteMapping("/restore")
    public ResponseEntity<JourFerierResponseDto> restoreJourFerier(@RequestParam Long id) throws JourFerierException {
        return ResponseEntity.ok(jourFerierService.restoreCalendar(id));
    }
}
