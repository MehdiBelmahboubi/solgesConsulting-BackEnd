package com.elmiraouy.jwtsecurity.Dto.request;

import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.enums.Civilite;
import com.elmiraouy.jwtsecurity.enums.Sexe;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class CollaboraterRequestDto {
    private Long id;
    private String matricule;
    private Civilite civilite;
    private String initiales;
    private String firstName;
    private String lastName;
    private LocalDateTime dateNaissance;
    private String lieuNaissance;
    private Sexe sexe;
    private String civNomPrenom;
    private String civPrenomNom;
    private String photos;
    private String signature;
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
    private Boolean nbEnfantsSaisi;
    private Integer nbEnfants;
    private Boolean nbEnfantsChargeSaisi;
    private Integer nbEnfantCharge;
    private String nomJeuneFille;
    private Integer nbPersCharge;
    private Boolean nbEpousesSaisi;
    private Integer nbEpouses;
    private LocalDateTime dateDeces;
    private LocalDateTime dateCertifDeces;
    private LocalDateTime dateNaturalisation;
    private Boolean active;
    private Boolean recrutable;
    private Boolean excluDeclaration;
    private String matriculeRecrutement;
    private Long countryCode1;
    private Long countryCode2;
    private Long companyId;
}
