package com.elmiraouy.jwtsecurity.Dto.response;

import com.elmiraouy.jwtsecurity.entities.Collaborater;
import com.elmiraouy.jwtsecurity.entities.ContractType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Builder
@Data
@AllArgsConstructor
public class ContractResponseDto {
    private Long id;
    private String contractRef;
    private String motifRecrutement;
    private LocalDateTime dateEntree;
    private Integer periodNegocible;
    private String regimeFiscal;
    private Integer exonerationFiscale;
    private String motifDepart;
    private LocalDateTime dateFin;
    private Boolean active;
    private Collaborater collaborater;
    private Long contractType;


    public ContractResponseDto(Long id, String contractRef, String motifRecrutement, LocalDateTime dateEntree, Integer periodNegocible, String regimeFiscal, Integer exonerationFiscale, String motifDepart, LocalDateTime dateFin,Long contractType) {
        this.id=id;
        this.contractRef=contractRef;
        this.motifRecrutement=motifRecrutement;
        this.dateEntree=dateEntree;
        this.periodNegocible=periodNegocible;
        this.regimeFiscal=regimeFiscal;
        this.exonerationFiscale=exonerationFiscale;
        this.motifDepart=motifDepart;
        this.dateFin=dateFin;
        this.contractType=contractType;
    }
}
