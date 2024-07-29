package com.elmiraouy.jwtsecurity.Dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ContractRequestDto {
    private String contractRef;
    private String motifRecrutement;
    private Date dateEntree;
    private Integer periodNegocible;
    private String regimeFiscal;
    private Integer exonerationFiscale;
    private String motifDepart;
    private Date dateFin;
    private Long collaboraterId;
    private Long contractType;
}
