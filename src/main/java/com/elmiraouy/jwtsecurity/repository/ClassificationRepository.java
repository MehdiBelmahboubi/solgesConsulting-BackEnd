package com.elmiraouy.jwtsecurity.repository;

import com.elmiraouy.jwtsecurity.entities.Classification;
import com.elmiraouy.jwtsecurity.entities.Collaborater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface ClassificationRepository extends JpaRepository<Classification,Long> {
    Optional<Classification> findByCollaboraterAndDateFinGreaterThan(Collaborater collaborater, Date currentDate);
}
