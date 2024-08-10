package com.elmiraouy.jwtsecurity.controller;

import com.elmiraouy.jwtsecurity.Dto.request.ContractRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.ContractResponseDto;
import com.elmiraouy.jwtsecurity.Dto.response.ContractTypeResponseDTO;
import com.elmiraouy.jwtsecurity.handlerException.CollaboraterException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
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
@RequestMapping("/api/client/contracts")
public class ContractController {

    private final ContractService contractService;

    @PostMapping
    public ResponseEntity<ContractResponseDto> addContractToCollaborater(@RequestBody ContractRequestDto contractRequestDto) throws ContractTypeException, CollaboraterException, ContractException, CompanyException {
        return ResponseEntity.ok(contractService.addContractToCollaborator(contractRequestDto));
    }

    @PutMapping
    public ResponseEntity<ContractResponseDto> updateContract(@RequestBody ContractRequestDto contractRequestDto) throws ContractTypeException, CollaboraterException ,ContractException{
        return ResponseEntity.ok(contractService.updateContract(contractRequestDto));
    }

    @GetMapping("/types")
    public ResponseEntity<List<ContractTypeResponseDTO>> getAllTypes(){
        return ResponseEntity.ok(contractService.getAllTypes());
    }
}
