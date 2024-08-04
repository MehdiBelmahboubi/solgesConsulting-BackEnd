package com.elmiraouy.jwtsecurity.repository;

import com.elmiraouy.jwtsecurity.entities.ClassificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClassificationTypeRepository extends JpaRepository<ClassificationType,Long> {
    @Query("""
        select c.id from ClassificationType c where c.nom like :nom
    """)
    Long findByNom(String nom);
}
