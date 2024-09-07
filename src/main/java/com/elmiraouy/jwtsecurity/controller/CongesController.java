package com.elmiraouy.jwtsecurity.controller;

import com.elmiraouy.jwtsecurity.Dto.request.CongesRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CongesResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.CalendarException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.service.CongesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/client/conges")
public class CongesController {

    private final CongesService congesService;

    @GetMapping("/getAll")
    public ResponseEntity<Page<CongesResponseDto>> getAll(
            @RequestParam Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(congesService.findByCompany(id, page, size));
    }

    @PostMapping
    public ResponseEntity<CongesResponseDto> createConges(@RequestBody CongesRequestDto congesRequestDto) throws CalendarException {
        return ResponseEntity.ok(congesService.createConges(congesRequestDto));
    }


}
