package com.elmiraouy.jwtsecurity.Dto.response;

import com.elmiraouy.jwtsecurity.enums.Sexe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
public class CollaboraterResponseDto {
    private Long id;
    private String matricule;
    private String civilite;
    private String initiales;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String lieuNaissance;
    private Sexe sexe;
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
    private Integer nbEnfantsSaisi;
    private Integer nbEnfants;
    private Integer nbEnfantsChargeSaisi;
    private Integer nbEnfantCharge;
    private String nomJeuneFille;
    private Integer nbPersCharge;
    private Integer nbEpousesSaisi;
    private Integer nbEpouses;
    private Date dateDeces;
    private Date dateCertifDeces;
    private String nationalite;
    private String nationalite2;
    private Date dateNaturalisation;
    private Boolean active;
    private Boolean recrutable;
    private Boolean excluDeclaration;
    private String matriculeRecrutement;
    private Boolean observation;
    private Date creeLe;
    private String creePar;
    private Date majLe;
    private String majPar;
    private Long companyId;
}
