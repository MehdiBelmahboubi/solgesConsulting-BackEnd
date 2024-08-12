package com.elmiraouy.jwtsecurity.entities;

import com.elmiraouy.jwtsecurity.enums.DroitType;
import com.elmiraouy.jwtsecurity.enums.Statut;
import com.elmiraouy.jwtsecurity.enums.Unite;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeId")
    private Calendar calendar;

    @OneToMany(mappedBy = "conges",fetch = FetchType.LAZY)
    private Collection<Droit> droits;
}
