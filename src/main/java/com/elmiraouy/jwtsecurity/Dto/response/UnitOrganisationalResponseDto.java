package com.elmiraouy.jwtsecurity.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class UnitOrganisationalResponseDto {
    private Long id ;
    private String name;
    private String longName;
    private String code;
    // uniform name example "society generale = SG"
    private String nomenclature;
    private String description;
    private Long createUserId;
    private Long updateUserId;
    private Long parentUnitOrganisationalId;
    private String note;
    private LocalDateTime createDateSys;
    private LocalDateTime updateDateSys;
    private LocalDateTime startDateSys;
    private LocalDateTime endDateSys;
    private Boolean active;
    private int level;
    private String address;
    private String parentUnityName;
    private LocalDateTime endDate;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime startDate;
    private String abbreviation;
    private String typeColor;
    private LocalDateTime decisionDate;
    //private Company company;

    private String typeUnitOrganisational;
    private Long typeUnitOrganisationalId;
    public UnitOrganisationalResponseDto(Long id, String name, String longName, String code, String nomenclature,
                                         String description, Long createUserId, Long updateUserId, String note
    ) {
        this.id = id;
        this.name = name;
        this.longName = longName;
        this.code = code;
        this.nomenclature = nomenclature;
        this.description = description;
        this.createUserId = createUserId;
        this.updateUserId = updateUserId;
        this.note = note;
    }
    public UnitOrganisationalResponseDto(Long id, String name, String longName, String code, String nomenclature,
                                         String description, Long createUserId, Long updateUserId, String note,
                                         int level,String typeUnitOrganisational,String parentUnityCode,Long typeUnitOrganisationalId,
                                         String abbreviation,Long parentUnitOrganisationalId,Boolean active, String typeColor
    ) {
        this.id = id;
        this.name = name;
        this.longName = longName;
        this.code = code;
        this.nomenclature = nomenclature;
        this.description = description;
        this.createUserId = createUserId;
        this.updateUserId = updateUserId;
        this.note = note;
        this.level=level;
        this.typeUnitOrganisational=typeUnitOrganisational;
        this.parentUnityName =parentUnityCode;
        this.typeUnitOrganisationalId=typeUnitOrganisationalId;
        this.abbreviation=abbreviation;
        this.parentUnitOrganisationalId=parentUnitOrganisationalId;
        this.active=active;
        this.typeColor=typeColor;
    }
    //private Collection<UnitOrganisational> filialUnitOrganisational;
    //private UnitOrganisational parentUnitOrganisational;
    // directeur principal
    //private AppUser manager;
}
