package com.elmiraouy.jwtsecurity.Dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ImageResponseDto {
    private Long id;
    private String url;

}
