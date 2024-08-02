package com.elmiraouy.jwtsecurity.repository;

import com.elmiraouy.jwtsecurity.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CountryRepository extends JpaRepository<Country,Long> {
    @Query("""
        select c.code from Country c where c.nationality like :nationnality
    """)
    public Long findByNationality(String nationnality);
}
