package com.elmiraouy.jwtsecurity.mappers;

import com.elmiraouy.jwtsecurity.Dto.response.UnitOrganisationalResponseDto;
import com.elmiraouy.jwtsecurity.entities.UnitOrganisational;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UnitOrganisationalDtoMapper implements Function<UnitOrganisational, UnitOrganisationalResponseDto> {
    @Override
    public UnitOrganisationalResponseDto apply(UnitOrganisational typeUnitOrganisational) {
        return new UnitOrganisationalResponseDto(
                typeUnitOrganisational.getId(),
                typeUnitOrganisational.getName(),
                typeUnitOrganisational.getLongName(),
                typeUnitOrganisational.getCode(),
                typeUnitOrganisational.getNomenclature(),
                typeUnitOrganisational.getDescription(),
                typeUnitOrganisational.getCreateUserId(),
                typeUnitOrganisational.getUpdateUserId(),
                typeUnitOrganisational.getNote()
        );
    }
}
