package com.elmiraouy.jwtsecurity.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contractRef;
    private String motifRecrutement;
    private Date dateEntree;
    private Integer periodNegocible;
    private String regimeFiscal;
    private Integer exonerationFiscale;
    private String motifDepart;
    private Date dateFin;
    //System - nv0
    private String observation;
    private LocalDateTime dateCreation;
    private String creePar;
    private LocalDateTime dateUpdate;
    private String majPar;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "collaborater_id")
    private Collaborater collaborater;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private ContractType contractType;
}
