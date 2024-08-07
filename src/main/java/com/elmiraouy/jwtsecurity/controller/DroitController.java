package com.elmiraouy.jwtsecurity.controller;

import com.elmiraouy.jwtsecurity.Dto.request.DroitRequestDto;
import com.elmiraouy.jwtsecurity.service.DroitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/client/conges")
public class DroitController {
    private final DroitService droitService;

    @PostMapping
    public ResponseEntity<DroitRequestDto> createDroit(@RequestBody DroitRequestDto droitRequestDto){
        return ResponseEntity.ok(droitService.createDroit(droitRequestDto));
    }
}
