package com.elmiraouy.jwtsecurity.mappers;

import com.elmiraouy.jwtsecurity.Dto.response.JourFerierResponseDto;
import com.elmiraouy.jwtsecurity.entities.JourFerier;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class JourFerierDtoMapper implements Function<JourFerier, JourFerierResponseDto> {
    @Override
    public JourFerierResponseDto apply(JourFerier jourFerier) {
        return new JourFerierResponseDto(
                jourFerier.getId(),
                jourFerier.getDateFete(),
                jourFerier.getNbrJour()
        );
    }
}
