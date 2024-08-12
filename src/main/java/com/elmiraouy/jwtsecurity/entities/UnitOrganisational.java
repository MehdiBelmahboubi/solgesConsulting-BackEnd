package com.elmiraouy.jwtsecurity.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnitOrganisational {
    @Min(1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name;
    private String longName;
    private String code;
    // uniform name example "society generale = SG"
    private String nomenclature;
    private String description;
    private Long createUserId;
    private Long updateUserId;
    private String note;
    private String abbreviation;
    private LocalDateTime createDateSys;
    private LocalDateTime updateDateSys;
    private LocalDateTime startDateSys;
    private LocalDateTime decisionDate;
    private LocalDateTime endDateSys;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;


    private Boolean active;
    private String address;
    private String typeColor;

    @ManyToOne
    private Company company;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private TypeUnitOrganisational typeUnitOrganisational;

    @OneToMany(mappedBy = "parentUnitOrganisational",fetch = FetchType.EAGER)
    private Collection<UnitOrganisational> filialUnitOrganisational=new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_Unit_Organisational_id")
    private UnitOrganisational parentUnitOrganisational;
    // directeur principal
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser manager;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Droit> droits= new ArrayList<>();



}
