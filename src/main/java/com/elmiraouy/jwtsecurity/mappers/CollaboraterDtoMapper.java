package com.elmiraouy.jwtsecurity.mappers;

import com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto;
import com.elmiraouy.jwtsecurity.entities.Collaborater;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class CollaboraterDtoMapper implements Function<Collaborater, CollaboraterResponseDto> {
    @Override
    public CollaboraterResponseDto apply(Collaborater collaborater) {
        return new CollaboraterResponseDto(
                collaborater.getId(),
                collaborater.getMatricule(),
                collaborater.getCivNomPrenom(),
                collaborater.getCnie(),
                collaborater.getEmail1(),
                collaborater.getTelephone(),
                collaborater.getInitiales(),
                collaborater.getLieuNaissance(),
                collaborater.getSexe()
        );
    }
}
