package com.elmiraouy.jwtsecurity.repository;

import com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto;
import com.elmiraouy.jwtsecurity.entities.Collaborater;
import com.elmiraouy.jwtsecurity.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CollaboraterRepository extends JpaRepository<Collaborater, Long> {
    Optional<Collaborater> findByMatriculeAndCompany(String matricule, Company company);
    Optional<Collaborater> findByCnie(String cnie);

    @Query("""
        select new com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto(
            c.id, c.matricule, c.civilite, c.initiales, c.civNomPrenom, c.dateNaissance,
            c.lieuNaissance, c.sexe, c.cnie, c.cnieDelivreeLe, c.cnieDelivreePar, c.cnieExpireLe,
            c.numPermisSejour, c.natPermisSejour, c.permisSejourDelivreLe, c.permisSejourDebVal,
            c.permisSejourFinVal, c.numPermisTravail, c.natPermisTravail, c.permisTravailDelivreLe,
            c.permisTravailDebVal, c.permisTravailFinVal, c.numPassePort, c.passePortDelivreLe, c.passePortExpireLe,
            c.passePortDelivrePar, c.telephone, c.tel1, c.tel2, c.tel3, c.email1, c.email2, c.email3, c.nbEnfantsSaisi,
            c.nbEnfants, c.nbEnfantsChargeSaisi, c.nbEnfantCharge, c.nomJeuneFille, c.nbPersCharge, c.nbEpousesSaisi, c.nbEpouses,
            c.dateNaturalisation, c.active, c.recrutable, c.excluDeclaration, c.matriculeRecrutement)
        from Collaborater c where c.company = :company
        """)
    List<CollaboraterResponseDto> findAllByCompany(Company company);
}
