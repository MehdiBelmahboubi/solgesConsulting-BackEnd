package com.elmiraouy.jwtsecurity.Dto.response;

import com.elmiraouy.jwtsecurity.entities.Calendar;
import com.elmiraouy.jwtsecurity.entities.Droit;
import com.elmiraouy.jwtsecurity.enums.Statut;
import com.elmiraouy.jwtsecurity.enums.Unite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CongesResponseDto {
    private Long id;
    private String code;
    private Boolean imputablePaix;
    private Statut statut;
    private LocalDateTime dateValidite;
    private LocalDateTime dateFinValidite;
    private Unite unite;
    private Boolean autoriserDefalcation;
    private Integer nbrDefalcation;
    private Boolean autoriserRecondiction;
    private Integer delaiRecondiction;
    private Integer minJour;
    private Integer maxJour;
    private Boolean reliquatReconduire;
    private Integer nbrAnneeReliquat;
    private List<Droit> droits;
    private Calendar calendar;
    public CongesResponseDto(Long id,String code,Boolean imputablePaix,Statut statut,LocalDateTime dateValidite,LocalDateTime dateFinValidite, Unite unite){
        this.id=id;
        this.code=code;
        this.imputablePaix=imputablePaix;
        this.statut=statut;
        this.dateValidite=dateValidite;
        this.dateFinValidite=dateFinValidite;
        this.unite=unite;
    }

}
