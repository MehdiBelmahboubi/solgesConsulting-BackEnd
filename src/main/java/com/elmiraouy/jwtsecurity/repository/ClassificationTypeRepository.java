package com.elmiraouy.jwtsecurity.repository;

import com.elmiraouy.jwtsecurity.entities.ClassificationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassificationTypeRepository extends JpaRepository<ClassificationType,Long> {
}
