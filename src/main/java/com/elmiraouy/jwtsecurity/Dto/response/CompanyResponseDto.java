package com.elmiraouy.jwtsecurity.Dto.response;


import com.elmiraouy.jwtsecurity.entities.AppUser;
import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.entities.UnitOrganisational;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class CompanyResponseDto {
    private Long id ;
    private String name;
    private String raisonSocial;
    private String code;
    private String titledCommercial;
    private String rc;
    private String ice;
    private String iss;
    private String tva;
    private String patent;
    private String cnss;
    private String codePostal;

    private Collection<AppUser> users;
    private Collection<Company> filialCompany;
    private Company parentCompany;
    private Collection<UnitOrganisational> unitOrganisationalList;


    public CompanyResponseDto(Long id, String name, String raisonSocial, String code, String titledCommercial, String rc, String ice, String iss, String tva, String cnss, String codePostal) {
        this.id = id;
        this.name = name;
        this.raisonSocial = raisonSocial;
        this.code = code;
        this.titledCommercial = titledCommercial;
        this.rc = rc;
        this.ice = ice;
        this.iss = iss;
        this.tva = tva;
        this.cnss = cnss;
        this.codePostal = codePostal;
    }
}
