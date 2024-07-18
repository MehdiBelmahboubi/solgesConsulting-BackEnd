package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.ClassificationRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.ClassificationResponseDto;
import com.elmiraouy.jwtsecurity.entities.Classification;
import com.elmiraouy.jwtsecurity.entities.ClassificationType;
import com.elmiraouy.jwtsecurity.entities.Collaborater;
import com.elmiraouy.jwtsecurity.handlerException.ClassificationException;
import com.elmiraouy.jwtsecurity.handlerException.ClassificationTypeException;
import com.elmiraouy.jwtsecurity.handlerException.CollaboraterException;
import com.elmiraouy.jwtsecurity.handlerException.ContractException;
import com.elmiraouy.jwtsecurity.mappers.ClassificationDtoMapper;
import com.elmiraouy.jwtsecurity.repository.ClassificationRepository;
import com.elmiraouy.jwtsecurity.repository.ClassificationTypeRepository;
import com.elmiraouy.jwtsecurity.repository.CollaboraterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ClassificationServiceImpl implements ClassificationService{
    private final ClassificationRepository classificationRepository;
    private final CollaboraterRepository collaboraterRepository;
    private final ClassificationDtoMapper classificationDtoMapper;
    private final ClassificationTypeRepository classificationTypeRepository;
    @Override
    public ClassificationResponseDto findByCollaborater(Long collaboraterId) throws CollaboraterException, ClassificationException {
        Collaborater collaborater = collaboraterRepository.findById(collaboraterId)
                .orElseThrow(() -> new CollaboraterException("Collaborater with this Id Introuvable: [%s] :".formatted(collaboraterId)));
        Date currentDate = new Date();
        Classification classification = classificationRepository.findByCollaboraterAndDateFinGreaterThan(collaborater,currentDate)
                .orElseThrow(() -> new ClassificationException("This User Dont Have a Current Classification"));
        return classificationDtoMapper.apply(classification);
    }

    @Override
    public ClassificationResponseDto addClassificationToCollaborater(ClassificationRequestDto request) throws CollaboraterException, ClassificationTypeException {
        Collaborater collaborater = collaboraterRepository.findById(request.getCollaboraterId())
                .orElseThrow(() -> new CollaboraterException("Collaborater avec  Id Introuvable: [%s] :".formatted(request.getCollaboraterId())));
        ClassificationType classificationType = classificationTypeRepository.findById(request.getTypeId())
                .orElseThrow(()->new ClassificationTypeException("classification avec  Id Introuvable: [%s] : ".formatted(request.getTypeId())));
        Classification classification = Classification.builder()
                .dateClassification(request.getDateClassification())
                .refClassification(request.getRefClassification())
                .categorieProf(request.getCategorieProf())
                .dateCategorieProf(request.getDateCategorieProf())
                .dateFin(request.getDateFin())
                .dateCreation(LocalDateTime.now())
                .collaborater(collaborater)
                .classificationType(classificationType)
                .build();
        return classificationDtoMapper.apply(classification);
    }
}
