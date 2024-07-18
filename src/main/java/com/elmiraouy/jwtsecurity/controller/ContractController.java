package com.elmiraouy.jwtsecurity.controller;

import com.elmiraouy.jwtsecurity.Dto.request.ContractRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.ContractResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.CollaboraterException;
import com.elmiraouy.jwtsecurity.handlerException.ContractException;
import com.elmiraouy.jwtsecurity.handlerException.ContractTypeException;
import com.elmiraouy.jwtsecurity.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/client/contract")
public class ContractController {

    private final ContractService contractService;

    @GetMapping("/get")
    public ResponseEntity<ContractResponseDto> findByCollaborater(@RequestParam Long id) throws CollaboraterException, ContractException {
        return ResponseEntity.ok(contractService.findByCollaborater(id));
    }

    @PostMapping("/add")
    public ResponseEntity<ContractResponseDto> addContractToCollaborater(@RequestBody ContractRequestDto contractRequestDto) throws ContractTypeException, CollaboraterException {
        return ResponseEntity.ok(contractService.addContractToCollaborator(contractRequestDto));
    }
}
