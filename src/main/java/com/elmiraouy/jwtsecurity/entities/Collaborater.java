package com.elmiraouy.jwtsecurity.entities;

import com.elmiraouy.jwtsecurity.enums.Civilite;
import com.elmiraouy.jwtsecurity.enums.Sexe;
import jakarta.persistence.*;
import lombok.*;

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
    private Civilite civilite;
    private String initiales;
    private String firstname;
    private String lastname;
    private Date dateNaissance;
    private String lieuNaissance;
    @Column(nullable = false)
    private Sexe sexe;
    @Column(nullable = false)
    private String civNomPrenom;
    @Column(nullable = false)
    private String civPrenomNom;
    private String photos;
    private String signature;
    //Info Immatriculation - nv2
    private String cnie;
    private Date cnieDelivreeLe;
    private Date cnieExpireLe;
    private String cnieDelivreePar;
    private String numPermisSejour;
    private String natPermisSejour;
    private Date permisSejourDelivreLe;
    private Date permisSejourDebVal;
    private Date permisSejourFinVal;
    private String numPermisTravail;
    private String natPermisTravail;
    private Date permisTravailDelivreLe;
    private Date permisTravailDebVal;
    private Date permisTravailFinVal;
    private String numPassePort;
    private Date passePortDelivreLe;
    private Date passePortExpireLe;
    private String passePortDelivrePar;
    //Coordonnees - nv3
    private String telephone;
    private String tel1;
    private String tel2;
    private String tel3;
    private String email1;
    private String email2;
    private String email3;
    //Famille - nv4
    private Boolean nbEnfantsSaisi;
    private Integer nbEnfants;
    private Boolean nbEnfantsChargeSaisi;
    private Integer nbEnfantCharge;
    private String nomJeuneFille;
    private Integer nbPersCharge;
    private Boolean nbEpousesSaisi;
    private Integer nbEpouses;
    //Other Infos - nv5
    private Date dateDeces;
    private Date dateCertifDeces;
    private String nationalite;
    private String nationalite2;
    private Date dateNaturalisation;
    private Boolean active;
    private Boolean recrutable;
    private Boolean excluDeclaration;
    private String matriculeRecrutement;
    //System - nv0
    private String observation;
    private Date dateCreation;
    private String creePar;
    private Date dateUpdate;
    private String majPar;

    @OneToMany(mappedBy = "collaborater",fetch = FetchType.LAZY)
    private Collection<Contract> contracts;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;
}
