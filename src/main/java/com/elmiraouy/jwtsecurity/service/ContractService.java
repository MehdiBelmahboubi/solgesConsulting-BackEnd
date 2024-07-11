package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.ContractRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.ContractResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.CollaboraterException;
import com.elmiraouy.jwtsecurity.handlerException.ContractException;
import com.elmiraouy.jwtsecurity.handlerException.ContractTypeException;

public interface ContractService {
    public ContractResponseDto findByCollaborater(Long collaboraterId) throws CollaboraterException,ContractException;
    public ContractResponseDto addContractToCollaborator(ContractRequestDto contractRequestDto) throws CollaboraterException, ContractTypeException;
    public ContractResponseDto deleteContractToCollaborater(ContractRequestDto contractRequestDto) throws CollaboraterException, ContractException;
}
