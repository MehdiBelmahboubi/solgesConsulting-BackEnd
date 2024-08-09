package com.elmiraouy.jwtsecurity.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class JourFerierResponseDto {
    private Long id;
    private LocalDateTime dateFete;
    private String nbrJour;
    private FeteResponseDto feteResponseDto;

    public JourFerierResponseDto(Long id,LocalDateTime dateFete,String nbrJour){
        this.id=id;
        this.dateFete=dateFete;
        this.nbrJour=nbrJour;
    }

    public JourFerierResponseDto(Long id,LocalDateTime dateFete,String nbrJour,String libelle){
        this.id=id;
        this.dateFete=dateFete;
        this.nbrJour=nbrJour;
        this.feteResponseDto.setLibelle(libelle);
    }
}
