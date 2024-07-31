package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.ClassificationRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.ClassificationResponseDto;
import com.elmiraouy.jwtsecurity.Dto.response.ClassificationTypeResponseDto;
import com.elmiraouy.jwtsecurity.entities.Classification;
import com.elmiraouy.jwtsecurity.entities.ClassificationType;
import com.elmiraouy.jwtsecurity.entities.Collaborater;
import com.elmiraouy.jwtsecurity.handlerException.ClassificationException;
import com.elmiraouy.jwtsecurity.handlerException.ClassificationTypeException;
import com.elmiraouy.jwtsecurity.handlerException.CollaboraterException;
import com.elmiraouy.jwtsecurity.handlerException.ContractException;
import com.elmiraouy.jwtsecurity.mappers.ClassificationDtoMapper;
import com.elmiraouy.jwtsecurity.mappers.ClassificationTypeDtoMapper;
import com.elmiraouy.jwtsecurity.repository.ClassificationRepository;
import com.elmiraouy.jwtsecurity.repository.ClassificationTypeRepository;
import com.elmiraouy.jwtsecurity.repository.CollaboraterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassificationServiceImpl implements ClassificationService{
    private final ClassificationRepository classificationRepository;
    private final CollaboraterRepository collaboraterRepository;
    private final ClassificationDtoMapper classificationDtoMapper;
    private final ClassificationTypeRepository classificationTypeRepository;
    private final ClassificationTypeDtoMapper classificationTypeDtoMapper;
    @Override
    public ClassificationResponseDto findByCollaborater(Long collaboraterId) throws CollaboraterException, ClassificationException {
//        Collaborater collaborater = collaboraterRepository.findById(collaboraterId)
//                .orElseThrow(() -> new CollaboraterException("Collaborater with this Id Introuvable: [%s] :".formatted(collaboraterId)));
//        Date currentDate = new Date();
//        Classification classification = classificationRepository.findByCollaboraterAndDateFinGreaterThan(collaborater,currentDate)
//                .orElseThrow(() -> new ClassificationException("This User Dont Have a Current Classification"));
//        return classificationDtoMapper.apply(classification);
        return null;
    }

    @Override
    public ClassificationResponseDto addClassificationToCollaborater(ClassificationRequestDto request) throws CollaboraterException, ClassificationTypeException, ClassificationException {
        Date currentDate = new Date();
        if (request.getDateFin().before(currentDate)) {
            throw new ClassificationException("Date fin is before current date");
        } else if (request.getDateClassification().after(request.getDateFin())) {
            throw new ClassificationException("Date Classification is after Date Fin");
        }

        Collaborater collaborater = collaboraterRepository.findById(request.getCollaboraterId())
                .orElseThrow(() -> new CollaboraterException("Collaborater avec  Id Introuvable: [%s] :".formatted(request.getCollaboraterId())));

        Classification activeClassification = classificationRepository.findByCollaboraterAndActive(collaborater,true);
        if(activeClassification != null)
        {
            activeClassification.setActive(false);
            classificationRepository.save(activeClassification);
        }

        ClassificationType classificationType = classificationTypeRepository.findById(request.getClassificationType())
                .orElseThrow(()->new ClassificationTypeException("Classification Type avec  Id Introuvable: [%s] : ".formatted(request.getClassificationType())));
        Classification classification = Classification.builder()
                .dateClassification(request.getDateClassification())
                .refClassification(request.getRefClassification())
                .categorieProf(request.getCategorieProf())
                .dateCategorieProf(request.getDateCategorieProf())
                .dateFin(request.getDateFin())
                .active(true)
                .dateCreation(LocalDateTime.now())
                .collaborater(collaborater)
                .classificationType(classificationType)
                .build();
        classificationRepository.save(classification);
        return classificationDtoMapper.apply(classification);
    }

    @Override
    public ClassificationResponseDto updateClassification(ClassificationRequestDto request) throws CollaboraterException, ClassificationTypeException, ClassificationException {
        Date currentDate = new Date();
        if (request.getDateFin().before(currentDate)) {
            throw new ClassificationException("Date fin is before current date");
        } else if (request.getDateClassification().after(request.getDateFin())) {
            throw new ClassificationException("Date Classification is after Date Fin");
        }
        Classification classification = classificationRepository.findById(request.getId())
                .orElseThrow(()->new ClassificationException("Classification avec  Id Introuvable: [%s] : ".formatted(request.getId())));
        Collaborater collaborater = collaboraterRepository.findById(request.getCollaboraterId())
                .orElseThrow(() -> new CollaboraterException("Collaborater avec  Id Introuvable: [%s] :".formatted(request.getCollaboraterId())));
        ClassificationType classificationType = classificationTypeRepository.findById(request.getClassificationType())
                .orElseThrow(()->new ClassificationTypeException("Classification Type avec  Id Introuvable: [%s] : ".formatted(request.getClassificationType())));
        classification.setDateClassification(request.getDateClassification());
        classification.setRefClassification(request.getRefClassification());
        classification.setCategorieProf(request.getCategorieProf());
        classification.setDateCategorieProf(request.getDateCategorieProf());
        classification.setDateFin(request.getDateFin());
        classification.setCollaborater(collaborater);
        classification.setClassificationType(classificationType);
        classificationRepository.save(classification);
        return classificationDtoMapper.apply(classification);
    }

    @Override
    public List<ClassificationTypeResponseDto> getAllTypes() {
        List<ClassificationType> classificationTypes = classificationTypeRepository.findAll();
        return classificationTypes.stream().map(classificationTypeDtoMapper).toList();
    }
}
