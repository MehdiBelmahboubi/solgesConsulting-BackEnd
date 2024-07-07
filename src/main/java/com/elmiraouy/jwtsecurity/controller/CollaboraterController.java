package com.elmiraouy.jwtsecurity.controller;

import com.elmiraouy.jwtsecurity.Dto.request.CollaboraterRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.CollaboraterException;
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

    @GetMapping("/getAll")
    public ResponseEntity<List<CollaboraterResponseDto>> getAllUsers() throws  CollaboraterException {
        return ResponseEntity.ok(collaboraterService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<CollaboraterResponseDto> createCollab(@RequestBody CollaboraterRequestDto collaboraterRequestDto) throws CollaboraterException{
        return ResponseEntity.ok(collaboraterService.createCollab(collaboraterRequestDto));
    }
}
