package com.elmiraouy.jwtsecurity.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class FeteResponseDto {
    private Long id;
    private String code;
    private String libelle;
    private TypeFeteResponseDto typeFeteResponseDto;

    public FeteResponseDto() {}
    public FeteResponseDto(Long id,String code,String libelle) {
        this.id=id;
        this.code=code;
        this.libelle=libelle;
    }
}
