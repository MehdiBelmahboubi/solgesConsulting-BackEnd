package com.elmiraouy.jwtsecurity.repository;

import com.elmiraouy.jwtsecurity.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
    @Query("""
        select c.code from Country c where c.nationality like :nationnality
    """)
    public Long findByNationality(String nationnality);

    public List<Country> findAllByActiveIsTrue();
}
