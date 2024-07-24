package com.elmiraouy.jwtsecurity.repository;

import com.elmiraouy.jwtsecurity.Dto.response.ClassificationResponseDto;
import com.elmiraouy.jwtsecurity.entities.Classification;
import com.elmiraouy.jwtsecurity.entities.Collaborater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface ClassificationRepository extends JpaRepository<Classification,Long> {
    @Query("""
        select new com.elmiraouy.jwtsecurity.Dto.response.ClassificationResponseDto(
        c.id,c.dateClassification,c.refClassification,c.categorieProf,
        c.dateCategorieProf,c.dateFin)
        from Classification c
        where c.collaborater =:collaborater and c.dateFin > :currentDate
        """)
    Optional<ClassificationResponseDto> findByCollaboraterAndDateFinGreaterThan(Collaborater collaborater, Date currentDate);
}
