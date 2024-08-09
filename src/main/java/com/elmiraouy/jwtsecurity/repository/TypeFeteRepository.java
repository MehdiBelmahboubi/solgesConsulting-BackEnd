package com.elmiraouy.jwtsecurity.repository;

import com.elmiraouy.jwtsecurity.entities.TypeFete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeFeteRepository extends JpaRepository<TypeFete,Long> {
}
