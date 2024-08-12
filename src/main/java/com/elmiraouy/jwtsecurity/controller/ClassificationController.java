package com.elmiraouy.jwtsecurity.controller;

import com.elmiraouy.jwtsecurity.Dto.request.ClassificationRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.ClassificationResponseDto;
import com.elmiraouy.jwtsecurity.Dto.response.ClassificationTypeResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.ClassificationException;
import com.elmiraouy.jwtsecurity.handlerException.ClassificationTypeException;
import com.elmiraouy.jwtsecurity.handlerException.CollaboraterException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.service.ClassificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/client/classifications")
public class ClassificationController {
    private final ClassificationService classificationService;

    @PostMapping
    public ResponseEntity<ClassificationResponseDto> addClassificationToCollaborater(@RequestBody ClassificationRequestDto classificationRequestDto) throws CollaboraterException, ClassificationTypeException, ClassificationException, CompanyException {
        return ResponseEntity.ok(classificationService.addClassificationToCollaborater(classificationRequestDto));
    }

    @PutMapping
    public ResponseEntity<ClassificationResponseDto> updateClassification(@RequestBody ClassificationRequestDto classificationRequestDto) throws CollaboraterException, ClassificationTypeException , ClassificationException {
        return ResponseEntity.ok(classificationService.updateClassification(classificationRequestDto));
    }

    @GetMapping("/types")
    public ResponseEntity<List<ClassificationTypeResponseDto>> getTypes(){
        return ResponseEntity.ok(classificationService.getAllTypes());
    }
}
