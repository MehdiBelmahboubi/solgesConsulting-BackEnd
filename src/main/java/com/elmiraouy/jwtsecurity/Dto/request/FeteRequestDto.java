package com.elmiraouy.jwtsecurity.Dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeteRequestDto {
    private Long id;
    private String code;
    private String libelle;
    private TypeFeteRequestDto typeFete;
    private Long typeId;
}
