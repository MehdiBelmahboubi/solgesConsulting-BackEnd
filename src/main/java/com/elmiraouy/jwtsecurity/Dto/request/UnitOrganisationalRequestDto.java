package com.elmiraouy.jwtsecurity.Dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class UnitOrganisationalRequestDto {
    private Long id ;
    private String name;
    private String longName;
    private String code;
    // uniform name example "society generale = SG"
    private String nomenclature;
    private String description;
    private Long createUserId;
    private String createUserEmail;
    private Long updateUserId;
    private String note;
    private String address;
    private LocalDateTime createDateSys;
    private LocalDateTime updateDateSys;
    private LocalDateTime startDateSys;
    private LocalDateTime endDateSys;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime decisionDate;
    private Boolean active;
    private Long parentCompanyId;
    private Long parentUnitOrganisationalId;
    private Long typeUnitOrganisationalId;
    private String abbreviation;
    private String typeColor;
}
