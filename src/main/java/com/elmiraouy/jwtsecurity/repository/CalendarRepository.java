package com.elmiraouy.jwtsecurity.repository;

import com.elmiraouy.jwtsecurity.Dto.response.CalendarResponseDto;
import com.elmiraouy.jwtsecurity.entities.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    @Query("""
    select new com.elmiraouy.jwtsecurity.Dto.response.CalendarResponseDto(
    c.id, c.code, c.libelle, c.jourFerier)
    from Calendar c where c.company.id = :companyId and c.active=:statut
    """)
    List<CalendarResponseDto> findByCompanyAndActive(Long companyId,Boolean statut);

    @Query("""
    select new com.elmiraouy.jwtsecurity.Dto.response.CalendarResponseDto(
    c.id, c.code, c.libelle, c.jourFerier)
    from Calendar c where c.id = :id and c.company.id = :companyId
    """)
    Calendar findByCodeAndCompany(Long id, Long companyId);
}
