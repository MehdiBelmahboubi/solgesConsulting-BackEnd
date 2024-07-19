package com.elmiraouy.jwtsecurity.mappers;

import com.elmiraouy.jwtsecurity.Dto.response.ClassificationTypeResponseDto;
import com.elmiraouy.jwtsecurity.entities.ClassificationType;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class ClassificationTypeDtoMapper implements Function<ClassificationType, ClassificationTypeResponseDto> {
    @Override
    public ClassificationTypeResponseDto apply(ClassificationType classificationType) {
        return new ClassificationTypeResponseDto(
                classificationType.getId(),
                classificationType.getNom()
        );
    }
}
