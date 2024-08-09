package com.elmiraouy.jwtsecurity.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JourFerier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateFete;
    private String nbrJour;
    private Boolean active;

    private String observation;
    private LocalDateTime dateCreation;
    private String creePar;
    private LocalDateTime dateUpdate;
    private String majPar;
    private Boolean addedInBulk;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fete_id")
    private Fete fete;
}
