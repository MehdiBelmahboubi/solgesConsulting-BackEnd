package com.elmiraouy.jwtsecurity.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id ;
    @Column
    private String roleName;
    @ManyToMany(mappedBy = "appRoles",fetch = FetchType.LAZY)
    private Collection<AppUser> users = new ArrayList<>();
}
