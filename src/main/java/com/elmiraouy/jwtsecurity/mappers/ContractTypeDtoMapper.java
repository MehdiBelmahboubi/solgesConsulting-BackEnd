package com.elmiraouy.jwtsecurity.mappers;

import com.elmiraouy.jwtsecurity.Dto.response.ContractTypeResponseDTO;
import com.elmiraouy.jwtsecurity.entities.ContractType;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ContractTypeDtoMapper implements Function<ContractType, ContractTypeResponseDTO> {
    @Override
    public ContractTypeResponseDTO apply(ContractType contractType) {
        return new ContractTypeResponseDTO(
                contractType.getId(),
                contractType.getCode()
        );
    }
}
