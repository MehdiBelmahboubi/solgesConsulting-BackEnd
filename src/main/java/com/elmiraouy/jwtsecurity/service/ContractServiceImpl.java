package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.ContractRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.ContractResponseDto;
import com.elmiraouy.jwtsecurity.Dto.response.ContractTypeResponseDTO;
import com.elmiraouy.jwtsecurity.entities.Collaborater;
import com.elmiraouy.jwtsecurity.entities.Contract;
import com.elmiraouy.jwtsecurity.entities.ContractType;
import com.elmiraouy.jwtsecurity.handlerException.CollaboraterException;
import com.elmiraouy.jwtsecurity.handlerException.ContractException;
import com.elmiraouy.jwtsecurity.handlerException.ContractTypeException;
import com.elmiraouy.jwtsecurity.mappers.ContractDtoMapper;
import com.elmiraouy.jwtsecurity.mappers.ContractTypeDtoMapper;
import com.elmiraouy.jwtsecurity.repository.CollaboraterRepository;
import com.elmiraouy.jwtsecurity.repository.ContractRepository;
import com.elmiraouy.jwtsecurity.repository.ContractTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService{
    private final ContractRepository contractRepository;
    private final CollaboraterRepository collaboraterRepository;
    private final ContractDtoMapper contractDtoMapper;
    private final ContractTypeRepository contractTypeRepository;
    private final ContractTypeDtoMapper contractTypeDtoMapper;

    @Override
    public ContractResponseDto findByCollaborater(Long collaboraterId) throws CollaboraterException,ContractException {
//        Collaborater collaborater = collaboraterRepository.findById(collaboraterId)
//                .orElseThrow(() -> new CollaboraterException("Collaborater with this Id Introuvable: [%s] :".formatted(collaboraterId)));
//        Date currentDate = new Date();
//        ContractResponseDto contract = contractRepository.findByCollaboraterAndDateFinGreaterThan(collaborater,currentDate)
//                .orElseThrow(() -> new ContractException("This User Dont Have a Current Contract"));
//        return contractDtoMapper.apply(contract);
        return null;
    }

    @Override
    public ContractResponseDto addContractToCollaborator(ContractRequestDto request) throws CollaboraterException, ContractTypeException {
        Collaborater collaborater = collaboraterRepository.findById(request.getCollaboraterId())
                .orElseThrow(() -> new CollaboraterException("Collaborater avec  Id Introuvable: [%s] :".formatted(request.getCollaboraterId())));
        ContractType contractType = contractTypeRepository.findById(request.getTypeId())
                .orElseThrow(() -> new ContractTypeException("Contract avec  Id Introuvable: [%s] : ".formatted(request.getTypeId())));
        Contract contract = Contract.builder()
                .contractRef(request.getContractRef())
                .motifRecrutement(request.getMotifRecrutement())
                .dateEntree(request.getDateEntree())
                .periodNegocible(request.getPeriodNegocible())
                .regimeFiscal(request.getRegimeFiscal())
                .exonerationFiscale(request.getExonerationFiscale())
                .motifDepart(request.getMotifDepart())
                .dateFin(request.getDateFin())
                .dateCreation(LocalDateTime.now())
                .collaborater(collaborater)
                .contractType(contractType)
                .build();
        contractRepository.save(contract);
        return contractDtoMapper.apply(contract);
    }

    @Override
    public ContractResponseDto deleteContractToCollaborater(ContractRequestDto contractRequestDto) throws CollaboraterException, ContractException {
        return null;
    }

    @Override
    public List<ContractTypeResponseDTO> getAllTypes() {
        List<ContractType> contractTypes = contractTypeRepository.findAll();
        return contractTypes.stream().map(contractTypeDtoMapper).toList();
    }
}
