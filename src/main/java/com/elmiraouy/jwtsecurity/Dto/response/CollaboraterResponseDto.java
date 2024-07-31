package com.elmiraouy.jwtsecurity.Dto.response;

import com.elmiraouy.jwtsecurity.entities.Contract;
import com.elmiraouy.jwtsecurity.enums.Civilite;
import com.elmiraouy.jwtsecurity.enums.Sexe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    private LocalDateTime dateNaissance;
    private String lieuNaissance;
    private Sexe sexe;
    private String cnie;
    private LocalDateTime cnieDelivreeLe;
    private String cnieDelivreePar;
    private LocalDateTime cnieExpireLe;
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
    private LocalDateTime dateNaturalisation;
    private Boolean active;
    private Boolean recrutable;
    private Boolean excluDeclaration;
    private String matriculeRecrutement;
    private Integer companyId;
    private ContractResponseDto contract;
    private ClassificationResponseDto classification;
    private List<CountryResponseDto> countries;


    public CollaboraterResponseDto(Long id, String matricule, Civilite civilite,String firstName,String lastName, String initiales, String civNomPrenom,
                                   LocalDateTime dateNaissance, String lieuNaissance, Sexe sexe, String cnie, LocalDateTime cnieDelivreeLe,
                                   String cnieDelivreePar, LocalDateTime cnieExpireLe, String numPermisSejour, String natPermisSejour,
                                   LocalDateTime permisSejourDelivreLe, LocalDateTime permisSejourDebVal, LocalDateTime permisSejourFinVal,
                                   String numPermisTravail, String natPermisTravail, LocalDateTime permisTravailDelivreLe,
                                   LocalDateTime permisTravailDebVal, LocalDateTime permisTravailFinVal, String numPassePort,
                                   LocalDateTime passePortDelivreLe, LocalDateTime passePortExpireLe, String passePortDelivrePar,
                                   String telephone, String tel1, String tel2, String tel3, String email1, String email2,
                                   String email3,String adresse1,String adresse2,String adresse3, Boolean nbEnfantsSaisi, Integer nbEnfants,
                                   Boolean nbEnfantsChargeSaisi, Integer nbEnfantCharge, String nomJeuneFille, Integer nbPersCharge,
                                   Boolean nbEpousesSaisi, Integer nbEpouses, LocalDateTime dateNaturalisation, Boolean active, Boolean recrutable,
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
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.adresse3 = adresse3;
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

    public CollaboraterResponseDto(Long id, String matricule, String civNomPrenom, String cnie, String firstName, String lastName, Civilite civilite, LocalDateTime dateNaissance, String initiales, String lieuNaissance, Sexe sexe) {
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
