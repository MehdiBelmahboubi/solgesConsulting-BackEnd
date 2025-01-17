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
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String codeAlpha1;
    private String codeAlpha2;
    private String name;
    private String capital;
    private String country;
    private String nationality;
    private Boolean active;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Collaborater> collaborators= new ArrayList<>();
}
