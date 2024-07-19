package com.elmiraouy.jwtsecurity.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ClassificationTypeResponseDto {
    private Long id;
    private String nom;
    private String description;

    public ClassificationTypeResponseDto(Long id, String nom) {
        this.id=id;
        this.nom=nom;
    }
}
