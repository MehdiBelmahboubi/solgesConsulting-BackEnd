package com.elmiraouy.jwtsecurity.Dto.request;

import com.elmiraouy.jwtsecurity.entities.ClassificationType;
import com.elmiraouy.jwtsecurity.entities.ContractType;
import com.elmiraouy.jwtsecurity.entities.TypeUnitOrganisational;
import com.elmiraouy.jwtsecurity.entities.UnitOrganisational;
import com.elmiraouy.jwtsecurity.enums.DroitType;
import com.elmiraouy.jwtsecurity.enums.Sexe;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder
public class DroitRequestDto {
    private Long id;
    private Integer nbrJour;
    private Sexe sexe;
    private String contractType;
    private String classificationType;
    private DroitType droitType;
    private Collection<ContractType> contractTypes = new ArrayList<>();
    private Collection<ClassificationType> classificationTypes = new ArrayList<>();
    private Collection<UnitOrganisational> unitOrganisationals = new ArrayList<>();
    private Collection<TypeUnitOrganisational> typeUnitOrganisationals = new ArrayList<>();
}
