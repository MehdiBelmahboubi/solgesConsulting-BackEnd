package com.elmiraouy.jwtsecurity.Dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class ContractRequestDto {
    private Long id;
    private String contractRef;
    private String motifRecrutement;
    private LocalDateTime dateEntree;
    private Integer periodNegocible;
    private String regimeFiscal;
    private Integer exonerationFiscale;
    private String motifDepart;
    private LocalDateTime dateFin;
    private Long collaboraterId;
    private Long contractType;
}
