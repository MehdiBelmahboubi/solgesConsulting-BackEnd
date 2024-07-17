package com.elmiraouy.jwtsecurity.Dto.response;

import com.elmiraouy.jwtsecurity.enums.Civilite;
import com.elmiraouy.jwtsecurity.enums.Sexe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollaboraterResponseDto {
    private Long id;
    private String matricule;
    private Civilite civilite;
    private String initiales;
    private String civNomPrenom;
    private String firstName;
    private String lastName;
    private Date dateNaissance;
    private String lieuNaissance;
    private Sexe sexe;
    private String cnie;
    private Date cnieDelivreeLe;
    private String cnieDelivreePar;
    private Date cnieExpireLe;
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
    private Date dateNaturalisation;
    private Boolean active;
    private Boolean recrutable;
    private Boolean excluDeclaration;
    private String matriculeRecrutement;
    private Integer companyId;


    public CollaboraterResponseDto(Long id, String matricule, Civilite civilite,String firstName,String lastName, String initiales, String civNomPrenom,
                                   Date dateNaissance, String lieuNaissance, Sexe sexe, String cnie, Date cnieDelivreeLe,
                                   String cnieDelivreePar, Date cnieExpireLe, String numPermisSejour, String natPermisSejour,
                                   Date permisSejourDelivreLe, Date permisSejourDebVal, Date permisSejourFinVal,
                                   String numPermisTravail, String natPermisTravail, Date permisTravailDelivreLe,
                                   Date permisTravailDebVal, Date permisTravailFinVal, String numPassePort,
                                   Date passePortDelivreLe, Date passePortExpireLe, String passePortDelivrePar,
                                   String telephone, String tel1, String tel2, String tel3, String email1, String email2,
                                   String email3, Boolean nbEnfantsSaisi, Integer nbEnfants, Boolean nbEnfantsChargeSaisi,
                                   Integer nbEnfantCharge, String nomJeuneFille, Integer nbPersCharge, Boolean nbEpousesSaisi,
                                   Integer nbEpouses, Date dateNaturalisation, Boolean active, Boolean recrutable,
                                   Boolean excluDeclaration, String matriculeRecrutement) {
        this.id = id;
        this.matricule = matricule;
        this.civilite = civilite;
        this.firstName=firstName;
        this.lastName=lastName;
        this.initiales = initiales;
        this.civNomPrenom = civNomPrenom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.sexe = sexe;
        this.cnie = cnie;
        this.cnieDelivreeLe = cnieDelivreeLe;
        this.cnieDelivreePar = cnieDelivreePar;
        this.cnieExpireLe = cnieExpireLe;
        this.numPermisSejour = numPermisSejour;
        this.natPermisSejour = natPermisSejour;
        this.permisSejourDelivreLe = permisSejourDelivreLe;
        this.permisSejourDebVal = permisSejourDebVal;
        this.permisSejourFinVal = permisSejourFinVal;
        this.numPermisTravail = numPermisTravail;
        this.natPermisTravail = natPermisTravail;
        this.permisTravailDelivreLe = permisTravailDelivreLe;
        this.permisTravailDebVal = permisTravailDebVal;
        this.permisTravailFinVal = permisTravailFinVal;
        this.numPassePort = numPassePort;
        this.passePortDelivreLe = passePortDelivreLe;
        this.passePortExpireLe = passePortExpireLe;
        this.passePortDelivrePar = passePortDelivrePar;
        this.telephone = telephone;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.tel3 = tel3;
        this.email1 = email1;
        this.email2 = email2;
        this.email3 = email3;
        this.nbEnfantsSaisi = nbEnfantsSaisi;
        this.nbEnfants = nbEnfants;
        this.nbEnfantsChargeSaisi = nbEnfantsChargeSaisi;
        this.nbEnfantCharge = nbEnfantCharge;
        this.nomJeuneFille = nomJeuneFille;
        this.nbPersCharge = nbPersCharge;
        this.nbEpousesSaisi = nbEpousesSaisi;
        this.nbEpouses = nbEpouses;
        this.dateNaturalisation = dateNaturalisation;
        this.active = active;
        this.recrutable = recrutable;
        this.excluDeclaration = excluDeclaration;
        this.matriculeRecrutement = matriculeRecrutement;
    }

    public CollaboraterResponseDto(Long id, String matricule, String civNomPrenom, String cnie, String firstName, String lastName, Civilite civilite, Date dateNaissance, String initiales, String lieuNaissance, Sexe sexe) {
        this.id=id;
        this.matricule=matricule;
        this.civNomPrenom=civNomPrenom;
        this.cnie=cnie;
        this.firstName=firstName;
        this.lastName=lastName;
        this.civilite=civilite;
        this.dateNaissance=dateNaissance;
        this.initiales=initiales;
        this.lieuNaissance=lieuNaissance;
        this.sexe=sexe;
    }
}
