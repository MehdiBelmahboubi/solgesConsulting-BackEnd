package com.elmiraouy.jwtsecurity.Dto.request;

import com.elmiraouy.jwtsecurity.enums.Statut;
import com.elmiraouy.jwtsecurity.enums.Unite;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Builder
public class CongesRequestDto {
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
    private Collection<DroitRequestDto> droits;
}
