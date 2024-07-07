package com.elmiraouy.jwtsecurity.repository;

import com.elmiraouy.jwtsecurity.entities.Collaborater;
import com.elmiraouy.jwtsecurity.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CollaboraterRepository extends JpaRepository<Collaborater,Long> {
    public Optional<Collaborater> findByMatriculeAndCompany(String matricule, Company company);
}
