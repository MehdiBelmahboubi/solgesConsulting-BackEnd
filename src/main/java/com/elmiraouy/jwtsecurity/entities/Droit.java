package com.elmiraouy.jwtsecurity.entities;

import com.elmiraouy.jwtsecurity.enums.DroitType;
import com.elmiraouy.jwtsecurity.enums.Sexe;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Droit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer nbrJour;
    private Sexe sexe;
    private String contractType;
    private String classificationType;
    private DroitType droitType;

    @ManyToMany(mappedBy = "droits",fetch = FetchType.EAGER)
    private Collection<ContractType> contractTypes= new ArrayList<>();

    @ManyToMany(mappedBy = "droits",fetch = FetchType.EAGER)
    private Collection<ClassificationType> classificationTypes= new ArrayList<>();

    @ManyToMany(mappedBy = "droits",fetch = FetchType.EAGER)
    private Collection<UnitOrganisational> unitOrganisationals= new ArrayList<>();

    @ManyToMany(mappedBy = "droits",fetch = FetchType.EAGER)
    private Collection<TypeUnitOrganisational> typeUnitOrganisationals= new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conges_id")
    private Conges conges;
}
