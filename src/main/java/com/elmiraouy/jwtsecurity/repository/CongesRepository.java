package com.elmiraouy.jwtsecurity.repository;

import com.elmiraouy.jwtsecurity.Dto.response.CongesResponseDto;
import com.elmiraouy.jwtsecurity.entities.Conges;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CongesRepository extends JpaRepository<Conges,Long> {
    @Query("""
    select new com.elmiraouy.jwtsecurity.Dto.response.CongesResponseDto(
        c.id,c.code,c.imputablePaix,c.statut,c.dateValidite,c.dateFinValidite,c.unite,c.autoriserDefalcation,
        c.nbrDefalcation,c.autoriserRecondiction,c.delaiRecondiction,c.minJour,c.maxJour,c.reliquatReconduire,
        c.nbrAnneeReliquat)
        from Conges c where c.company.id=:companyId and c.statut = "Encours"
    """)
    Page<CongesResponseDto> findAllByCompanyAndActive(Long companyId, Pageable pageable);
}
