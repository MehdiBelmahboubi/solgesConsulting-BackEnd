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
import java.util.List;

@Data
@Builder
public class DroitRequestDto {
    private Long id;
    private Integer nbrJour;
    private List<Sexe> sexes;
    private List<String> contractTypes;
    private List<String> classificationTypes;
    private DroitType droitType;
}
