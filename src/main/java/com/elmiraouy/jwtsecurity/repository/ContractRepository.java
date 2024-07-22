package com.elmiraouy.jwtsecurity.repository;

import com.elmiraouy.jwtsecurity.Dto.response.ContractResponseDto;
import com.elmiraouy.jwtsecurity.entities.Collaborater;
import com.elmiraouy.jwtsecurity.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract,Long> {
    @Query("""
        select new com.elmiraouy.jwtsecurity.Dto.response.ContractResponseDto(
        c.id, c.contractRef, c.motifRecrutement, c.dateEntree, c.periodNegocible, c.regimeFiscal,
        c.exonerationFiscale, c.motifDepart, c.dateFin)
        from Contract c 
        where c.collaborater = :collaborater and c.dateFin > :currentDate
        """)
    Optional<ContractResponseDto> findByCollaboraterAndDateFinGreaterThan(Collaborater collaborater, Date currentDate);
}
