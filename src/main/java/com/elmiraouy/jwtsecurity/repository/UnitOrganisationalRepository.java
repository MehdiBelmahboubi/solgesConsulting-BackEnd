package com.elmiraouy.jwtsecurity.repository;
import com.elmiraouy.jwtsecurity.Dto.response.UnitOrganisationalResponseDto;
import com.elmiraouy.jwtsecurity.entities.UnitOrganisational;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnitOrganisationalRepository extends JpaRepository<UnitOrganisational,Long> {

     @Query("""
                select t from UnitOrganisational t
                where upper(t.name) =upper(:nameOrCode)
                or upper(t.code) =upper(:nameOrCode)
     """)
    Optional<UnitOrganisational> findByNameOrCode(String nameOrCode);


}
