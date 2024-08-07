package com.elmiraouy.jwtsecurity.entities;

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
public class ClassificationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private Boolean active;

    @OneToMany(mappedBy = "classificationType",fetch = FetchType.LAZY)
    private Collection<Classification> classifications;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Droit> droits= new ArrayList<>();
}
