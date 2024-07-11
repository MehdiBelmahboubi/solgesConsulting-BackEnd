package com.elmiraouy.jwtsecurity.mappers;

import com.elmiraouy.jwtsecurity.Dto.response.ContractResponseDto;
import com.elmiraouy.jwtsecurity.entities.Contract;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ContractDtoMapper implements Function<Contract, ContractResponseDto> {

    @Override
    public ContractResponseDto apply(Contract contract) {
        return new ContractResponseDto(
                contract.getId(),
                contract.getContractRef(),
                contract.getMotifRecrutement(),
                contract.getDateEntree(),
                contract.getPeriodNegocible(),
                contract.getRegimeFiscal(),
                contract.getExonerationFiscale(),
                contract.getMotifDepart(),
                contract.getDateFin()
        );
    }
}
