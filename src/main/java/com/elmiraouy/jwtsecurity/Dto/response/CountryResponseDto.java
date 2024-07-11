package com.elmiraouy.jwtsecurity.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CountryResponseDto {
    private Long code;
    private String codeAlpha1;
    private String codeAlpha2;
    private String name;
    private String capital;
    private String country;
    private String nationality;

    public CountryResponseDto(Long code, String nationality) {
        this.code=code;
        this.nationality=nationality;
    }
}
