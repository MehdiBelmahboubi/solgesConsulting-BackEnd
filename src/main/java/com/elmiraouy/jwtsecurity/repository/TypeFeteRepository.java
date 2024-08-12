package com.elmiraouy.jwtsecurity.repository;

import com.elmiraouy.jwtsecurity.Dto.response.TypeFeteResponseDto;
import com.elmiraouy.jwtsecurity.entities.TypeFete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeFeteRepository extends JpaRepository<TypeFete,Long> {
    @Query("""
    select new com.elmiraouy.jwtsecurity.Dto.response.TypeFeteResponseDto(
    t.id,t.libelle,t.reconduction)
    from TypeFete  t where t.company.id=:companyId and t.active=true
    """)
    List<TypeFeteResponseDto> findByCompanyAndActive(Long companyId);
}
