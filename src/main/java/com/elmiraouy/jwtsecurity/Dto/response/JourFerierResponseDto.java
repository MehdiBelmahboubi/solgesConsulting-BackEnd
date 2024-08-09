package com.elmiraouy.jwtsecurity.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class JourFerierResponseDto {
    private Long id;
    private LocalDateTime dateFete;
    private String nbrJour;
}
