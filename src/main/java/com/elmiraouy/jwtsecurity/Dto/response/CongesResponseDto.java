package com.elmiraouy.jwtsecurity.Dto.response;

import com.elmiraouy.jwtsecurity.entities.CalendarType;
import com.elmiraouy.jwtsecurity.entities.Droit;
import com.elmiraouy.jwtsecurity.enums.DroitType;
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
    private CalendarType calendarType;
    public CongesResponseDto(Long id,String code,Boolean imputablePaix,Statut statut,LocalDateTime dateValidite,LocalDateTime dateFinValidite,
                             Unite unite,Boolean autoriserDefalcation,Integer nbrDefalcation,Boolean autoriserRecondiction,Integer delaiRecondiction,
                             Integer minJour,Integer maxJour,Boolean reliquatReconduire,Integer nbrAnneeReliquat){
        this.id=id;
        this.code=code;
        this.imputablePaix=imputablePaix;
        this.statut=statut;
        this.dateValidite=dateValidite;
        this.dateFinValidite=dateFinValidite;
        this.unite=unite;
        this.autoriserDefalcation=autoriserDefalcation;
        this.nbrDefalcation=nbrDefalcation;
        this.autoriserRecondiction=autoriserRecondiction;
        this.delaiRecondiction=delaiRecondiction;
        this.minJour=minJour;
        this.maxJour=maxJour;
        this.reliquatReconduire=reliquatReconduire;
        this.nbrAnneeReliquat=nbrAnneeReliquat;
    }

}
