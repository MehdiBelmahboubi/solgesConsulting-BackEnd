package com.elmiraouy.jwtsecurity.entities;

import com.elmiraouy.jwtsecurity.enums.DayOfWeek;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String  libelle;
    private Boolean jourFerier;

    private Boolean active;
    private LocalDateTime dateCreation;

    @ElementCollection(targetClass = DayOfWeek.class)
    @CollectionTable(name = "calendar_days_of_week", joinColumns = @JoinColumn(name = "calendar_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week")
    private Set<DayOfWeek> daysOfWeek;

    @OneToMany(mappedBy = "calendar",fetch = FetchType.LAZY)
    private Collection<Conges> conges;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;
}
