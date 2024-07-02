package com.elmiraouy.jwtsecurity.Dto.response;

import java.util.Date;

public class CollaborateurResponseDto {
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

    public CollaborateurResponseDto(Long id, String matricule, String civilite, String initiales, String nom, String prenom,
                                    String sexe, Date dateNaissance, String lieuNaissance, Date dateDeces, Date dateCertifDeces,
                                    String nationalite, String nationalite2, Date dateNaturalisation, String cnie, Date cnieDelivreeLe,
                                    Date cnieExpireLe, String cnieDelivreePar, String numPermisSejour, String natPermisSejour,
                                    Date permisSejourDelivreLe, Date permisSejourDebVal, Date permisSejourFinVal, String numPermisTravail,
                                    String natPermisTravail, Date permisTravailDelivreLe, Date permisTravailDebVal, Date permisTravailFinVal,
                                    String numPassePort, Date passePortDelivreLe, Date passePortExpireLe, String passePortDelivrePar,
                                    Integer nbEnfantsSaisi, Integer nbEnfants, Integer nbEnfantsChargeSaisi, Integer nbEnfantCharge,
                                    String nomJeuneFille, Integer nbPersCharge, Integer nbEpousesSaisi, Integer nbEpouses, Boolean active,
                                    String telephone, String tel1, String tel2, String tel3, String email1, String email2, String email3,
                                    Boolean recrutable, Boolean excluDeclaration, String matriculeRecrutement, String observation, Date creeLe,
                                    String creePar, Date majLe, String majPar, String civNomPrenom, String civPrenomNom, String phots) {
        this.id = id;
        this.matricule = matricule;
        this.civilite = civilite;
        this.initiales = initiales;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.dateDeces = dateDeces;
        this.dateCertifDeces = dateCertifDeces;
        this.nationalite = nationalite;
        this.nationalite2 = nationalite2;
        this.dateNaturalisation = dateNaturalisation;
        this.cnie = cnie;
        this.cnieDelivreeLe = cnieDelivreeLe;
        this.cnieExpireLe = cnieExpireLe;
        this.cnieDelivreePar = cnieDelivreePar;
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
        this.nbEnfantsSaisi = nbEnfantsSaisi;
        this.nbEnfants = nbEnfants;
        this.nbEnfantsChargeSaisi = nbEnfantsChargeSaisi;
        this.nbEnfantCharge = nbEnfantCharge;
        this.nomJeuneFille = nomJeuneFille;
        this.nbPersCharge = nbPersCharge;
        this.nbEpousesSaisi = nbEpousesSaisi;
        this.nbEpouses = nbEpouses;
        this.active = active;
        this.telephone = telephone;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.tel3 = tel3;
        this.email1 = email1;
        this.email2 = email2;
        this.email3 = email3;
        this.recrutable = recrutable;
        this.excluDeclaration = excluDeclaration;
        this.matriculeRecrutement = matriculeRecrutement;
        this.observation = observation;
        this.creeLe = creeLe;
        this.creePar = creePar;
        this.majLe = majLe;
        this.majPar = majPar;
        this.civNomPrenom = civNomPrenom;
        this.civPrenomNom = civPrenomNom;
        this.phots = phots;
    }
}
