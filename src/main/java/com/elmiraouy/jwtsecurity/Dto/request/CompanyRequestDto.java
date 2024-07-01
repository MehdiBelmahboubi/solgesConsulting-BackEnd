package com.elmiraouy.jwtsecurity.Dto.request;

import com.elmiraouy.jwtsecurity.entities.AppUser;
import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.entities.Image;
import com.elmiraouy.jwtsecurity.entities.UnitOrganisational;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequestDto {
    public String number;
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
    private String address;
    private Image image;
    private AppUser client;
    private Collection<Company> filialCompany;
    private Company parentCompany;
    private Collection<UnitOrganisational> unitOrganisationalList;
    private AppUserRequestDto admin;

}
