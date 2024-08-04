package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.ContractRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.ContractResponseDto;
import com.elmiraouy.jwtsecurity.Dto.response.ContractTypeResponseDTO;
import com.elmiraouy.jwtsecurity.handlerException.CollaboraterException;
import com.elmiraouy.jwtsecurity.handlerException.ContractException;
import com.elmiraouy.jwtsecurity.handlerException.ContractTypeException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContractService {
    public ContractResponseDto findByCollaborater(Long collaboraterId) throws CollaboraterException,ContractException;
    public ContractResponseDto addContractToCollaborator(ContractRequestDto contractRequestDto) throws CollaboraterException, ContractTypeException, ContractException;
    public ContractResponseDto updateContract(ContractRequestDto contractRequestDto) throws CollaboraterException, ContractTypeException, ContractException;
    public ContractResponseDto deleteContractToCollaborater(ContractRequestDto contractRequestDto) throws CollaboraterException, ContractException;
    public List<ContractTypeResponseDTO> getAllTypes();

    void persistFromFile(MultipartFile file, String table);
}
