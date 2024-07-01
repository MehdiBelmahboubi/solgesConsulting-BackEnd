package com.elmiraouy.jwtsecurity.repository;

import com.elmiraouy.jwtsecurity.entities.AppUser;
import com.elmiraouy.jwtsecurity.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

     @Query("""
     select u from Company u where upper(u.name) =upper(:name)
     """)
    Optional<Company> findByName(String name);


}
