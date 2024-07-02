package com.elmiraouy.jwtsecurity.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Collaborateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricule;
    private String civilite;
    private String initiales;
    private String nom;
    private String prenom;
    private String sexe;
    private Date dateNaissance;
    private String lieuNaissance;
    private Date dateDeces;
    private Date dateCertifDeces;
    private String nationalite;
    private String nationalite2;
    private Date dateNaturalisation;
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
    private Integer nbEnfantsSaisi;
    private Integer nbEnfants;
    private Integer nbEnfantsChargeSaisi;
    private Integer nbEnfantCharge;
    private String nomJeuneFille;
    private Integer nbPersCharge;
    private Integer nbEpousesSaisi;
    private Integer nbEpouses;
    private Boolean active;
    private String telephone;
    private String tel1;
    private String tel2;
    private String tel3;
    private String email1;
    private String email2;
    private String email3;
    private Boolean recrutable;
    private Boolean excluDeclaration;
    private String matriculeRecrutement;
    private String observation;
    private Date creeLe;
    private String creePar;
    private Date majLe;
    private String majPar;
    private String civNomPrenom;
    private String civPrenomNom;
    private String phots;
}
