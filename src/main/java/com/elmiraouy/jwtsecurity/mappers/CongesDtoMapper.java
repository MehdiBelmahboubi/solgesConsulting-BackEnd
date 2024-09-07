package com.elmiraouy.jwtsecurity.mappers;

import com.elmiraouy.jwtsecurity.Dto.response.CongesResponseDto;
import com.elmiraouy.jwtsecurity.entities.Conges;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class CongesDtoMapper implements Function<Conges, CongesResponseDto> {
    @Override
    public CongesResponseDto apply(Conges conges) {
        return new CongesResponseDto(
                conges.getId(),
                conges.getCode(),
                conges.getImputablePaix(),
                conges.getStatut(),
                conges.getDateValidite(),
                conges.getDateFinValidite(),
                conges.getUnite()
        );
    }
}
