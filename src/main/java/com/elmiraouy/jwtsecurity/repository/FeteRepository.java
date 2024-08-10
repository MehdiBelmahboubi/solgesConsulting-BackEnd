package com.elmiraouy.jwtsecurity.repository;

import com.elmiraouy.jwtsecurity.Dto.response.FeteResponseDto;
import com.elmiraouy.jwtsecurity.entities.Fete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeteRepository extends JpaRepository<Fete,Long> {
    @Query("""
    select new com.elmiraouy.jwtsecurity.Dto.response.FeteResponseDto(
    f.id,f.libelle,f.code)
    from Fete f where f.company.id=:companyId and f.active=true
    """)
    List<FeteResponseDto> findByCompanyAndActive(Long companyId);
}
