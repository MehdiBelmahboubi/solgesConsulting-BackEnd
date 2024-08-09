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
public class Fete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String libelle;
    private Boolean active;

    private String observation;
    private LocalDateTime dateCreation;
    private String creePar;
    private LocalDateTime dateUpdate;
    private String majPar;
    private Boolean addedInBulk;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private TypeFete typeFete;

    @OneToMany(mappedBy = "fete",fetch = FetchType.LAZY)
    private Collection<JourFerier> jourFeriers;
}
