package com.elmiraouy.jwtsecurity.entities;

import com.elmiraouy.jwtsecurity.enums.DroitType;
import com.elmiraouy.jwtsecurity.enums.Sexe;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @ElementCollection(targetClass = Sexe.class)
    @Enumerated(EnumType.STRING)
    private List<Sexe> sexes;

    @ElementCollection
    private List<String> contractTypes;

    @ElementCollection
    private List<String> classificationTypes;

    @Enumerated(EnumType.STRING)
    private DroitType droitType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conges_id")
    private Conges conges;
}
