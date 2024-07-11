package com.elmiraouy.jwtsecurity.Dto.request;

import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.enums.Civilite;
import com.elmiraouy.jwtsecurity.enums.Sexe;
import jakarta.persistence.Column;
import lombok.*;
import java.util.Date;

@Data
@Builder
public class CollaboraterRequestDto {
    private String matricule;
    private Civilite civilite;
    private String initiales;
    private String firstname;
    private String lastname;
    private Date dateNaissance;
    private String lieuNaissance;
    private String civNomPrenom;
    private String civPrenomNom;
    private String photos;
    private String signature;
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
    private String telephone;
    private String tel1;
    private String tel2;
    private String tel3;
    private String email1;
    private String email2;
    private String email3;
    private Boolean nbEnfantsSaisi;
    private Integer nbEnfants;
    private Boolean nbEnfantsChargeSaisi;
    private Integer nbEnfantCharge;
    private String nomJeuneFille;
    private Integer nbPersCharge;
    private Boolean nbEpousesSaisi;
    private Integer nbEpouses;
    private Date dateDeces;
    private Date dateCertifDeces;
    private Date dateNaturalisation;
    private Boolean active;
    private Boolean recrutable;
    private Boolean excluDeclaration;
    private String matriculeRecrutement;
    private Long countryCode1;
    private Long countryCode2;
    private Long company_id;
}
