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
    private LocalDateTime formatedDateFete;
    private String nbrJour;
    private FeteResponseDto fete;

    public JourFerierResponseDto(Long id,LocalDateTime dateFete,String nbrJour){
        this.id=id;
        this.dateFete=dateFete;
        this.nbrJour=nbrJour;
    }

    public JourFerierResponseDto(Long id,LocalDateTime dateFete,String nbrJour,String libelle){
        this.id=id;
        this.dateFete = dateFete;
        this.nbrJour=nbrJour;
        this.fete = new FeteResponseDto();
        this.fete.setLibelle(libelle);
    }
}
