package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.ClassificationRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.ClassificationResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.ClassificationException;
import com.elmiraouy.jwtsecurity.handlerException.ClassificationTypeException;
import com.elmiraouy.jwtsecurity.handlerException.CollaboraterException;

public interface ClassificationService {
    public ClassificationResponseDto findByCollaborater(Long collaboraterId) throws CollaboraterException, ClassificationException;
    public ClassificationResponseDto addClassificationToCollaborater(ClassificationRequestDto classificationRequestDto) throws CollaboraterException, ClassificationTypeException;
}
