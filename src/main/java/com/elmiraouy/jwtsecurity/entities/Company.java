package com.elmiraouy.jwtsecurity.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name;
    private String raisonSocial;
    // abriviation
    private String code;
    private String titledCommercial;
    private String rc;
    private String ice;
    // a changer le nom is mot cle dans SQL
    private String iss;
    private String tva;
    private String patent;
    private String number;
    private String cnss;
    private String codePostal;
    private String urlImage;
    private String address;
    private LocalDateTime dateCreation;
    private LocalDateTime dateUpdate;
    private LocalDateTime dateBlocked;
    private LocalDateTime dateUnblocked;
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<AppUser> users=new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser admin;
    @OneToMany(mappedBy = "parentCompany",fetch = FetchType.LAZY)
    private Collection<Company> filialCompany;
    @ManyToOne
    @JoinColumn(name = "parent_company_id")
    private Company parentCompany;
    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
    private Collection<UnitOrganisational> unitOrganisationalList=new ArrayList<>();
    @OneToOne
    private Image image;
    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
    private Collection<TypeUnitOrganisational> unitTypes;
    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
    private Collection<Collaborater> collaboraters;
    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
    private Collection<Conges> conges;
    public Company(Long id, String name, String raisonSocial, String code, String titledCommercial, String rc, String ice, String iss, String tva, String patent, String cnss, String codePostal,String number) {
        this.id = id;
        this.name = name;
        this.raisonSocial = raisonSocial;
        this.code = code;
        this.titledCommercial = titledCommercial;
        this.rc = rc;
        this.ice = ice;
        this.iss = iss;
        this.tva = tva;
        this.patent = patent;
        this.cnss = cnss;
        this.codePostal = codePostal;
        this.number=number;
    }
}
