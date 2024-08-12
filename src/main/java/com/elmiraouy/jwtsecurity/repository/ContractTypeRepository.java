package com.elmiraouy.jwtsecurity.repository;

import com.elmiraouy.jwtsecurity.entities.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractTypeRepository extends JpaRepository<ContractType,Long> {
    @Query("""
        select c.id from ContractType c where c.code like :code
    """)
    Long findByCode(String code);

    List<ContractType> findAllByActiveIsTrue();
}
