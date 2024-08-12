package com.elmiraouy.jwtsecurity.repository;

import com.elmiraouy.jwtsecurity.Dto.response.JourFerierResponseDto;
import com.elmiraouy.jwtsecurity.entities.JourFerier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourFerierRepository extends JpaRepository<JourFerier,Long> {
    @Query("""
    select new com.elmiraouy.jwtsecurity.Dto.response.JourFerierResponseDto(
    j.id,j.dateFete,j.nbrJour,j.fete.libelle)
    from JourFerier j where j.company.id=:companyId and j.active=true
    """)
    List<JourFerierResponseDto> findByCompanyAndActive(Long companyId);
}
