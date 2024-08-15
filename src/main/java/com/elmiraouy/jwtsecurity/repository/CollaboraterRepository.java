package com.elmiraouy.jwtsecurity.repository;

import com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto;
import com.elmiraouy.jwtsecurity.entities.Collaborater;
import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.enums.Sexe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface CollaboraterRepository extends JpaRepository<Collaborater, Long> {
    @Query("""
    select c from Collaborater c where c.cnie = :cnie or (c.matricule = :matricule and c.company.id=:companyId)
    """)
    Optional<Collaborater> findByMatriculeAndCompanyIdOrCnie(String matricule,Long companyId,String cnie);
    Optional<Collaborater> findByMatriculeAndCompanyId(String matricule, Long companyId);
    Optional<Collaborater> findByCnie(String cnie);

    @Query("""
        select new com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto(
            c.id, c.matricule, c.civilite,c.firstName,c.lastName, c.initiales, c.civNomPrenom, c.dateNaissance,
            c.lieuNaissance, c.sexe, c.cnie, c.cnieDelivreeLe, c.cnieDelivreePar, c.cnieExpireLe,
            c.numPermisSejour, c.natPermisSejour, c.permisSejourDelivreLe, c.permisSejourDebVal,
            c.permisSejourFinVal, c.numPermisTravail, c.natPermisTravail, c.permisTravailDelivreLe,
            c.permisTravailDebVal, c.permisTravailFinVal, c.numPassePort, c.passePortDelivreLe, c.passePortExpireLe,
            c.passePortDelivrePar, c.telephone, c.tel1, c.tel2, c.tel3, c.email1, c.email2, c.email3,c.adresse1,c.adresse2,c.adresse3, c.nbEnfantsSaisi,
            c.nbEnfants, c.nbEnfantsChargeSaisi, c.nbEnfantCharge, c.nomJeuneFille, c.nbPersCharge, c.nbEpousesSaisi, c.nbEpouses,
            c.dateNaturalisation, c.active, c.recrutable, c.excluDeclaration, c.matriculeRecrutement)
        from Collaborater c where c.company.id= :companyId and c.active=:active
        """)
    Page<CollaboraterResponseDto> findAllByCompanyAndActive(Long companyId,Boolean active, Pageable pageable);

    @Query("""
        select c.id from Collaborater c where c.matricule like :matricule
    """)
    Long findByMatricule(String matricule);

    @Query("""
       select new com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto(
            c.id, c.matricule, c.civilite,c.firstName,c.lastName, c.initiales, c.civNomPrenom, c.dateNaissance,
            c.lieuNaissance, c.sexe, c.cnie, c.cnieDelivreeLe, c.cnieDelivreePar, c.cnieExpireLe,
            c.numPermisSejour, c.natPermisSejour, c.permisSejourDelivreLe, c.permisSejourDebVal,
            c.permisSejourFinVal, c.numPermisTravail, c.natPermisTravail, c.permisTravailDelivreLe,
            c.permisTravailDebVal, c.permisTravailFinVal, c.numPassePort, c.passePortDelivreLe, c.passePortExpireLe,
            c.passePortDelivrePar, c.telephone, c.tel1, c.tel2, c.tel3, c.email1, c.email2, c.email3,c.adresse1,c.adresse2,c.adresse3, c.nbEnfantsSaisi,
            c.nbEnfants, c.nbEnfantsChargeSaisi, c.nbEnfantCharge, c.nomJeuneFille, c.nbPersCharge, c.nbEpousesSaisi, c.nbEpouses,
            c.dateNaturalisation, c.active, c.recrutable, c.excluDeclaration, c.matriculeRecrutement)
        from Collaborater c
        where c.company.id = :companyId
        and c.active = :active
        and (
            LOWER(c.matricule) like LOWER(CONCAT('%', :search, '%')) or
            LOWER(c.firstName) like LOWER(CONCAT('%', :search, '%')) or
            LOWER(c.lastName) like LOWER(CONCAT('%', :search, '%')) or
            LOWER(c.initiales) like LOWER(CONCAT('%', :search, '%')) or
            LOWER(c.civNomPrenom) like LOWER(CONCAT('%', :search, '%')) or
            LOWER(c.lieuNaissance) like LOWER(CONCAT('%', :search, '%')) or
            LOWER(c.email1) like LOWER(CONCAT('%', :search, '%')) or
            LOWER(c.cnie) like LOWER(CONCAT('%', :search, '%')) or
            LOWER(c.telephone) like LOWER(CONCAT('%', :search, '%'))
        )
        """)
    Page<CollaboraterResponseDto> findAllByCompanyAndActiveAndSearch(Long companyId, Boolean active, Pageable pageable, String search);

    @Query("""
        select new com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto(
            c.id, c.matricule, c.civilite,c.firstName,c.lastName, c.initiales, c.civNomPrenom, c.dateNaissance,
            c.lieuNaissance, c.sexe, c.cnie, c.cnieDelivreeLe, c.cnieDelivreePar, c.cnieExpireLe,
            c.numPermisSejour, c.natPermisSejour, c.permisSejourDelivreLe, c.permisSejourDebVal,
            c.permisSejourFinVal, c.numPermisTravail, c.natPermisTravail, c.permisTravailDelivreLe,
            c.permisTravailDebVal, c.permisTravailFinVal, c.numPassePort, c.passePortDelivreLe, c.passePortExpireLe,
            c.passePortDelivrePar, c.telephone, c.tel1, c.tel2, c.tel3, c.email1, c.email2, c.email3,c.adresse1,c.adresse2,c.adresse3, c.nbEnfantsSaisi,
            c.nbEnfants, c.nbEnfantsChargeSaisi, c.nbEnfantCharge, c.nomJeuneFille, c.nbPersCharge, c.nbEpousesSaisi, c.nbEpouses,
            c.dateNaturalisation, c.active, c.recrutable, c.excluDeclaration, c.matriculeRecrutement)
        from Collaborater c join c.contracts con
        where c.company.id= :companyId and c.active=:active and con.contractType.id=:selectedOption
         group by c, con.contractType
        """)
    Page<CollaboraterResponseDto> findAllByCompanyAndActiveGroupedByContract(Long companyId, Boolean active, Pageable pageable,String selectedOption);

    @Query("""
        select new com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto(
            c.id, c.matricule, c.civilite,c.firstName,c.lastName, c.initiales, c.civNomPrenom, c.dateNaissance,
            c.lieuNaissance, c.sexe, c.cnie, c.cnieDelivreeLe, c.cnieDelivreePar, c.cnieExpireLe,
            c.numPermisSejour, c.natPermisSejour, c.permisSejourDelivreLe, c.permisSejourDebVal,
            c.permisSejourFinVal, c.numPermisTravail, c.natPermisTravail, c.permisTravailDelivreLe,
            c.permisTravailDebVal, c.permisTravailFinVal, c.numPassePort, c.passePortDelivreLe, c.passePortExpireLe,
            c.passePortDelivrePar, c.telephone, c.tel1, c.tel2, c.tel3, c.email1, c.email2, c.email3,c.adresse1,c.adresse2,c.adresse3, c.nbEnfantsSaisi,
            c.nbEnfants, c.nbEnfantsChargeSaisi, c.nbEnfantCharge, c.nomJeuneFille, c.nbPersCharge, c.nbEpousesSaisi, c.nbEpouses,
            c.dateNaturalisation, c.active, c.recrutable, c.excluDeclaration, c.matriculeRecrutement)
        from Collaborater c join c.classifications clas
        where c.company.id= :companyId and c.active=:active and clas.classificationType.id=:selectedOption
        group by c, clas.classificationType
        """)
    Page<CollaboraterResponseDto> findAllByCompanyAndActiveGroupedByClassification(Long companyId, Boolean active, Pageable pageable,String selectedOption);

    @Query("""
        select new com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto(
            c.id, c.matricule, c.civilite,c.firstName,c.lastName, c.initiales, c.civNomPrenom, c.dateNaissance,
            c.lieuNaissance, c.sexe, c.cnie, c.cnieDelivreeLe, c.cnieDelivreePar, c.cnieExpireLe,
            c.numPermisSejour, c.natPermisSejour, c.permisSejourDelivreLe, c.permisSejourDebVal,
            c.permisSejourFinVal, c.numPermisTravail, c.natPermisTravail, c.permisTravailDelivreLe,
            c.permisTravailDebVal, c.permisTravailFinVal, c.numPassePort, c.passePortDelivreLe, c.passePortExpireLe,
            c.passePortDelivrePar, c.telephone, c.tel1, c.tel2, c.tel3, c.email1, c.email2, c.email3,c.adresse1,c.adresse2,c.adresse3, c.nbEnfantsSaisi,
            c.nbEnfants, c.nbEnfantsChargeSaisi, c.nbEnfantCharge, c.nomJeuneFille, c.nbPersCharge, c.nbEpousesSaisi, c.nbEpouses,
            c.dateNaturalisation, c.active, c.recrutable, c.excluDeclaration, c.matriculeRecrutement)
        from Collaborater c
        where c.company.id= :companyId and c.active=:active and c.sexe=:selectedOption
        """)
    Page<CollaboraterResponseDto> findAllByCompanyAndActiveGroupedBySexe(Long companyId,Boolean active,Pageable pageable,Sexe selectedOption);

//    @Query("""
//        select new com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto(
//            c.id, c.matricule, c.civilite,c.firstName,c.lastName, c.initiales, c.civNomPrenom, c.dateNaissance,
//            c.lieuNaissance, c.sexe, c.cnie, c.cnieDelivreeLe, c.cnieDelivreePar, c.cnieExpireLe,
//            c.numPermisSejour, c.natPermisSejour, c.permisSejourDelivreLe, c.permisSejourDebVal,
//            c.permisSejourFinVal, c.numPermisTravail, c.natPermisTravail, c.permisTravailDelivreLe,
//            c.permisTravailDebVal, c.permisTravailFinVal, c.numPassePort, c.passePortDelivreLe, c.passePortExpireLe,
//            c.passePortDelivrePar, c.telephone, c.tel1, c.tel2, c.tel3, c.email1, c.email2, c.email3,c.adresse1,c.adresse2,c.adresse3, c.nbEnfantsSaisi,
//            c.nbEnfants, c.nbEnfantsChargeSaisi, c.nbEnfantCharge, c.nomJeuneFille, c.nbPersCharge, c.nbEpousesSaisi, c.nbEpouses,
//            c.dateNaturalisation, c.active, c.recrutable, c.excluDeclaration, c.matriculeRecrutement)
//        from Collaborater c where c.company.id= :companyId and c.active=:active
//        group by c.contracts , c.classifications
//        """)
//    Page<CollaboraterResponseDto> findAllByCompanyAndActiveGroupedByContractAndClassification(Long companyId, Boolean active, Pageable pageable,String selectedOption);
}
