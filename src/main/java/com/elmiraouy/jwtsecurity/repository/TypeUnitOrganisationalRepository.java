package com.elmiraouy.jwtsecurity.repository;
import com.elmiraouy.jwtsecurity.Dto.response.TypeUnitOrganisationalResponseDto;
import com.elmiraouy.jwtsecurity.entities.TypeUnitOrganisational;
import com.elmiraouy.jwtsecurity.entities.UnitOrganisational;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeUnitOrganisationalRepository extends JpaRepository<TypeUnitOrganisational,Long> {

     @Query("""
                select t from TypeUnitOrganisational t
                where (upper(t.name) =upper(:name) or upper(t.code) =upper(:code) or t.level=:level)
                and t.active=true
     """)
    TypeUnitOrganisational findByNameOrCodeOrLevelIsActive(String name, String code, int level);
    @Query("""
                select new com.elmiraouy.jwtsecurity.Dto.response.TypeUnitOrganisationalResponseDto
                (t.id,t.name,t.code,t.company.id,t.level,t.active,
                t.createDate,t.updateDate,t.startDate,t.endDate,t.color) from TypeUnitOrganisational t
                join Company c  on  t.company.id=c.id
                where t.company.id=:companyId
     """)
    List<TypeUnitOrganisationalResponseDto> findByCompanyId(Long companyId);
}
