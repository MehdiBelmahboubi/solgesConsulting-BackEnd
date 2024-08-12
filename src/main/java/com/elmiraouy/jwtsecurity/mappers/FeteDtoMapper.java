package com.elmiraouy.jwtsecurity.mappers;

import com.elmiraouy.jwtsecurity.Dto.response.FeteResponseDto;
import com.elmiraouy.jwtsecurity.entities.Fete;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class FeteDtoMapper implements Function<Fete, FeteResponseDto> {
    @Override
    public FeteResponseDto apply(Fete fete) {
        return new FeteResponseDto(
                fete.getId(),
                fete.getCode(),
                fete.getLibelle()
        );
    }
}
