package com.elmiraouy.jwtsecurity.Dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class ClassificationRequestDto {
    private Long id;
    private LocalDateTime dateClassification;
    private String refClassification;
    private String categorieProf;
    private LocalDateTime dateCategorieProf;
    private LocalDateTime dateFin;
    private Long collaboraterId;
    private Long classificationType;
}
