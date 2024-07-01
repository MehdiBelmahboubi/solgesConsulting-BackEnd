package com.elmiraouy.jwtsecurity.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;


@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeUnitOrganisational {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String code;
    @Column(nullable = true)
    private Integer level;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime decisionDate;
    @Column(nullable = true)
    private Boolean active;
    private Long userCreatedId;
    private Long userUpdatedId;
    @OneToMany(mappedBy = "typeUnitOrganisational",fetch = FetchType.LAZY)
    private Collection<UnitOrganisational> unitOrganisationalCollection;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    private String color;
}
