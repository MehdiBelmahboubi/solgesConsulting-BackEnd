package com.elmiraouy.jwtsecurity.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TypeFeteResponseDto {
    private Long id;
    private String libelle;
    private Boolean reconduction;
}
