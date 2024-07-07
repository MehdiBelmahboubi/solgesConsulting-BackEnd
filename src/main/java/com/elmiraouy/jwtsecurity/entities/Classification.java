package com.elmiraouy.jwtsecurity.entities;

import jakarta.persistence.*;
import lombok.*;

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

    private Date dateClassification;
    private String refClassification;
    private String categorieProf;
    private Date dateCategorieProf;
    private Date dateFin;

    //System - nv0
    private String observation;
    private Date dateCreation;
    private String creePar;
    private Date dateUpdate;
    private String majPar;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private ClassificationType classificationType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "collaborater_id")
    private Collaborater collaborater;
}
