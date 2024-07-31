package com.elmiraouy.jwtsecurity.entities;

import com.elmiraouy.jwtsecurity.enums.Civilite;
import com.elmiraouy.jwtsecurity.enums.Sexe;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Collaborater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Info Personnels - nv1
    @Column(nullable = false)
    private String matricule;
    @Enumerated(EnumType.STRING)
    private Civilite civilite;
    private String initiales;
    private String firstName;
    private String lastName;
    private LocalDateTime dateNaissance;
    private String lieuNaissance;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexe sexe;
    @Column(nullable = false)
    private String civNomPrenom;
    @Column(nullable = false)
    private String civPrenomNom;
    private String nomJeuneFille;
    private String photos;
    private String signature;
    //Info Immatriculation - nv2
    private String cnie;
    private LocalDateTime cnieDelivreeLe;
    private LocalDateTime cnieExpireLe;
    private String cnieDelivreePar;
    private String numPermisSejour;
    private String natPermisSejour;
    private LocalDateTime permisSejourDelivreLe;
    private LocalDateTime permisSejourDebVal;
    private LocalDateTime permisSejourFinVal;
    private String numPermisTravail;
    private String natPermisTravail;
    private LocalDateTime permisTravailDelivreLe;
    private LocalDateTime permisTravailDebVal;
    private LocalDateTime permisTravailFinVal;
    private String numPassePort;
    private LocalDateTime passePortDelivreLe;
    private LocalDateTime passePortExpireLe;
    private String passePortDelivrePar;
    //Coordonnees - nv3
    private String telephone;
    private String tel1;
    private String tel2;
    private String tel3;
    private String email1;
    private String email2;
    private String email3;
    private String adresse1;
    private String adresse2;
    private String adresse3;
    //Famille - nv4
    private Boolean nbEnfantsSaisi;
    private Integer nbEnfants;
    private Boolean nbEnfantsChargeSaisi;
    private Integer nbEnfantCharge;
    private Boolean nbEpousesSaisi;
    private Integer nbEpouses;
    private Integer nbPersCharge;
    //Other Infos - nv5
    private LocalDateTime dateDeces;
    private LocalDateTime dateCertifDeces;
    private LocalDateTime dateNaturalisation;
    private Boolean active;
    private Boolean recrutable;
    private Boolean excluDeclaration;
    private String matriculeRecrutement;
    //System - nv0
    private String observation;
    private LocalDateTime dateCreation;
    private String creePar;
    private LocalDateTime dateUpdate;
    private String majPar;
    private Boolean addedInBulk;

    @OneToMany(mappedBy = "collaborater",fetch = FetchType.LAZY)
    private Collection<Contract> contracts;

    @OneToMany(mappedBy = "collaborater",fetch = FetchType.LAZY)
    private Collection<Classification> classifications;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany(mappedBy = "collaborators",fetch = FetchType.EAGER)
    private Collection<Country> countries= new ArrayList<>();
}
