package com.elmiraouy.jwtsecurity.mappers;

import com.elmiraouy.jwtsecurity.Dto.response.ClassificationResponseDto;
import com.elmiraouy.jwtsecurity.entities.Classification;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ClassificationDtoMapper implements Function<Classification, ClassificationResponseDto> {
    @Override
    public ClassificationResponseDto apply(Classification classification) {
        return new ClassificationResponseDto(
                classification.getId(),
                classification.getDateClassification(),
                classification.getRefClassification(),
                classification.getCategorieProf(),
                classification.getDateCategorieProf(),
                classification.getDateFin(),
                classification.getClassificationType().getId()
        );
    }
}
