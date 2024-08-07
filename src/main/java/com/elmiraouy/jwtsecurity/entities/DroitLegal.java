package com.elmiraouy.jwtsecurity.entities;

import com.elmiraouy.jwtsecurity.enums.Sexe;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DroitLegal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer nbrJour;
    private Sexe sexe;
    private String contractType;
    private String classificationType;
}
