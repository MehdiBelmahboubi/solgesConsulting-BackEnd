package com.elmiraouy.jwtsecurity.Dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class JourFerierRequestDto {
    private Long id;
    private LocalDateTime dateFete;
    private String nbrJour;
    private FeteRequestDto fete;
    private Long feteId;
}
