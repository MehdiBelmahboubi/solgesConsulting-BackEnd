package com.elmiraouy.jwtsecurity.Dto.response;

import com.elmiraouy.jwtsecurity.entities.ClassificationType;
import com.elmiraouy.jwtsecurity.entities.Collaborater;
import com.elmiraouy.jwtsecurity.entities.ContractType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Builder
@Data
@AllArgsConstructor
public class ClassificationResponseDto {
    private Long id;
    private Date dateClassification;
    private String refClassification;
    private String categorieProf;
    private Date dateCategorieProf;
    private Date dateFin;
    private Collaborater collaborater;
    private ClassificationType classificationType;

    public ClassificationResponseDto(Long id, Date dateClassification, String refClassification, String categorieProf, Date dateCategorieProf, Date dateFin) {
        this.id=id;
        this.dateClassification=dateClassification;
        this.refClassification=refClassification;
        this.categorieProf=categorieProf;
        this.dateCategorieProf=dateCategorieProf;
        this.dateFin=dateFin;
    }
}
