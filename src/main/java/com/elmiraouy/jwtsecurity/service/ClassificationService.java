package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.ClassificationRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.ClassificationResponseDto;
import com.elmiraouy.jwtsecurity.Dto.response.ClassificationTypeResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.ClassificationException;
import com.elmiraouy.jwtsecurity.handlerException.ClassificationTypeException;
import com.elmiraouy.jwtsecurity.handlerException.CollaboraterException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClassificationService {
    public ClassificationResponseDto addClassificationToCollaborater(ClassificationRequestDto classificationRequestDto) throws CollaboraterException, ClassificationTypeException, ClassificationException;
    public ClassificationResponseDto updateClassification(ClassificationRequestDto classificationRequestDto) throws CollaboraterException, ClassificationTypeException, ClassificationException;
    public List<ClassificationTypeResponseDto> getAllTypes();

    void persistFromFile(MultipartFile file, String table);
}
