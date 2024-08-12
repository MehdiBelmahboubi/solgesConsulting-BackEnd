package com.elmiraouy.jwtsecurity.Dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeFeteRequestDto {
    private Long id;
    private String libelle;
    private Boolean reconduction;
    private Long companyId;
}
