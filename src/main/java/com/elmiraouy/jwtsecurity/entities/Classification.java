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
public class Classification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateClassification;
    private String refClassification;
    private String categorieProf;
    private LocalDateTime dateCategorieProf;
    private LocalDateTime dateFin;
    private Boolean active;

    //System - nv0
    private String observation;
    private LocalDateTime dateCreation;
    private String creePar;
    private LocalDateTime dateUpdate;
    private String majPar;
    private Boolean addedInBulk;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private ClassificationType classificationType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "collaborater_id")
    private Collaborater collaborater;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;
}
