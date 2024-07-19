package com.elmiraouy.jwtsecurity.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ContractTypeResponseDTO {
    private Long id;
    private String code;
    private String description;

    public ContractTypeResponseDTO(Long id, String code) {
        this.id=id;
        this.code=code;
    }
}
