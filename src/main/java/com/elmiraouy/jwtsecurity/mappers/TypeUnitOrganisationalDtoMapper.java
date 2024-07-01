package com.elmiraouy.jwtsecurity.mappers;

import com.elmiraouy.jwtsecurity.Dto.response.TypeUnitOrganisationalResponseDto;
import com.elmiraouy.jwtsecurity.entities.TypeUnitOrganisational;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class TypeUnitOrganisationalDtoMapper  implements Function<TypeUnitOrganisational, TypeUnitOrganisationalResponseDto> {
    @Override
    public TypeUnitOrganisationalResponseDto apply(TypeUnitOrganisational typeUnitOrganisational) {
        return new TypeUnitOrganisationalResponseDto(typeUnitOrganisational.getId(),
                typeUnitOrganisational.getName(),
                typeUnitOrganisational.getCode(),
                typeUnitOrganisational.getCompany().getId(),
                typeUnitOrganisational.getLevel(),
                typeUnitOrganisational.getActive(),
                typeUnitOrganisational.getCreateDate(),
                typeUnitOrganisational.getUpdateDate(),
                typeUnitOrganisational.getStartDate(),
                typeUnitOrganisational.getEndDate(),
                typeUnitOrganisational.getColor());
    }
}
