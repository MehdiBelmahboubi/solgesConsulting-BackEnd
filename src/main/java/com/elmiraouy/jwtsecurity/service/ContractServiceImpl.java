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
    public ContractResponseDto addContractToCollaborator(ContractRequestDto request) throws CollaboraterException, ContractTypeException, ContractException {
        Date currentDate = new Date();
        if (request.getDateFin().before(currentDate)) {
            throw new ContractException("Date fin is before current date");
        } else if (request.getDateEntree().after(request.getDateFin())) {
            throw new ContractException("Date Entree is after Date Fin");
        }

        Collaborater collaborater = collaboraterRepository.findById(request.getCollaboraterId())
                .orElseThrow(() -> new CollaboraterException("Collaborater avec Id Introuvable: [%s]".formatted(request.getCollaboraterId())));

        Contract activeContract = contractRepository.findByCollaboraterAndActive(collaborater, true);
        if (activeContract != null) {
            activeContract.setActive(false);
            contractRepository.save(activeContract);
        }

        ContractType contractType = contractTypeRepository.findById(request.getContractType())
                .orElseThrow(() -> new ContractTypeException("ContractType avec Id Introuvable: [%s]".formatted(request.getContractType())));

        Contract newContract = Contract.builder()
                .contractRef(request.getContractRef())
                .motifRecrutement(request.getMotifRecrutement())
                .dateEntree(request.getDateEntree())
                .periodNegocible(request.getPeriodNegocible())
                .regimeFiscal(request.getRegimeFiscal())
                .exonerationFiscale(request.getExonerationFiscale())
                .motifDepart(request.getMotifDepart())
                .dateFin(request.getDateFin())
                .active(true)
                .dateCreation(LocalDateTime.now())
                .collaborater(collaborater)
                .contractType(contractType)
                .build();

        contractRepository.save(newContract);
        return contractDtoMapper.apply(newContract);
    }


    @Override
    public ContractResponseDto updateContract(ContractRequestDto request) throws CollaboraterException, ContractTypeException, ContractException {
        Date currentDate = new Date();
        if (request.getDateFin().before(currentDate)) {
            throw new ContractException("Date fin is before current date");
        } else if (request.getDateEntree().after(request.getDateFin())) {
            throw new ContractException("Date Entree is after Date Fin");
        }
        Contract contract = contractRepository.findById(request.getId())
                .orElseThrow(()->new ContractException("Contract with this Id Introuvable: [%s] :".formatted(request.getId())));
        Collaborater collaborater = collaboraterRepository.findById(request.getCollaboraterId())
                .orElseThrow(() -> new CollaboraterException("Collaborater avec Id Introuvable: [%s]".formatted(request.getCollaboraterId())));
        ContractType contractType = contractTypeRepository.findById(request.getContractType())
                .orElseThrow(() -> new ContractTypeException("ContractType avec Id Introuvable: [%s]".formatted(request.getContractType())));
        contract.setContractRef(request.getContractRef());
        contract.setMotifRecrutement(request.getMotifRecrutement());
        contract.setDateEntree(request.getDateEntree());
        contract.setPeriodNegocible(request.getPeriodNegocible());
        contract.setRegimeFiscal(request.getRegimeFiscal());
        contract.setExonerationFiscale(request.getExonerationFiscale());
        contract.setMotifDepart(request.getMotifDepart());
        contract.setDateFin(request.getDateFin());
        contract.setCollaborater(collaborater);
        contract.setContractType(contractType);
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
