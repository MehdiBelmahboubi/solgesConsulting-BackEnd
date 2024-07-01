package com.elmiraouy.jwtsecurity.repository;

import com.elmiraouy.jwtsecurity.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {

     @Query("""
     select u from AppUser u where upper(u.email) =upper(:email)
     """)
    Optional<AppUser> findByEmail(String email);

    Optional<AppUser> findAppUserById(Long id);


}
