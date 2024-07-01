package com.elmiraouy.jwtsecurity.controller;
import com.elmiraouy.jwtsecurity.Dto.request.AppUserRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.AppUserResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.AppUserException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.EntityNotFoundException;
import com.elmiraouy.jwtsecurity.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/client/users")
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping
    public ResponseEntity<List<AppUserResponseDto>> getAllUsers() throws AppUserException {
        return ResponseEntity.ok(appUserService.findAll());
    }
    @PostMapping("/register")
    public ResponseEntity<AppUserResponseDto> register(@RequestBody AppUserRequestDto appUserRequestDto) throws AppUserException, EntityNotFoundException, CompanyException {
        return ResponseEntity.ok(appUserService.register(appUserRequestDto));
    }


}
