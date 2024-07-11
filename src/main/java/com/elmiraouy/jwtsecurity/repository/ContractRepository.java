package com.elmiraouy.jwtsecurity.repository;

import com.elmiraouy.jwtsecurity.entities.Collaborater;
import com.elmiraouy.jwtsecurity.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract,Long> {
    Optional<Contract> findByCollaboraterAndDateFinGreaterThan(Collaborater collaborater, Date currentDate);
}
