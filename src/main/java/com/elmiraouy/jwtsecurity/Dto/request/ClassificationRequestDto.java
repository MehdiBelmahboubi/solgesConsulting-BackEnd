package com.elmiraouy.jwtsecurity.Dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ClassificationRequestDto {
    private Long id;
    private Date dateClassification;
    private String refClassification;
    private String categorieProf;
    private Date dateCategorieProf;
    private Date dateFin;
    private Long collaboraterId;
    private Long classificationType;
}
