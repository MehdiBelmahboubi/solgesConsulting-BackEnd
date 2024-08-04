package com.elmiraouy.jwtsecurity.Dto.response;

import com.elmiraouy.jwtsecurity.entities.ClassificationType;
import com.elmiraouy.jwtsecurity.entities.Collaborater;
import com.elmiraouy.jwtsecurity.entities.ContractType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Builder
@Data
@AllArgsConstructor
public class ClassificationResponseDto {
    private Long id;
    private LocalDateTime dateClassification;
    private String refClassification;
    private String categorieProf;
    private LocalDateTime dateCategorieProf;
    private LocalDateTime dateFin;
    private Boolean active;
    private Collaborater collaborater;
    private Long classificationType;

    public ClassificationResponseDto(Long id, LocalDateTime dateClassification, String refClassification, String categorieProf, LocalDateTime dateCategorieProf, LocalDateTime dateFin,Long classificationType) {
        this.id=id;
        this.dateClassification=dateClassification;
        this.refClassification=refClassification;
        this.categorieProf=categorieProf;
        this.dateCategorieProf=dateCategorieProf;
        this.dateFin=dateFin;
        this.classificationType=classificationType;
    }
}
