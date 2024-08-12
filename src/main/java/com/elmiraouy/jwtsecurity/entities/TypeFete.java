package com.elmiraouy.jwtsecurity.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeFete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String libelle;
    @Column(nullable = false)
    private Boolean reconduction;
    private Boolean active;

    private String observation;
    private LocalDateTime dateCreation;
    private String creePar;
    private LocalDateTime dateUpdate;
    private String majPar;
    private Boolean addedInBulk;

    @OneToMany(mappedBy = "typeFete",fetch = FetchType.LAZY)
    private Collection<Fete> fetes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;
}
