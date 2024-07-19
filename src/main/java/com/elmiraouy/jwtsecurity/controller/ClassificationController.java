package com.elmiraouy.jwtsecurity.controller;

import com.elmiraouy.jwtsecurity.Dto.request.ClassificationRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.ClassificationResponseDto;
import com.elmiraouy.jwtsecurity.Dto.response.ClassificationTypeResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.ClassificationException;
import com.elmiraouy.jwtsecurity.handlerException.ClassificationTypeException;
import com.elmiraouy.jwtsecurity.handlerException.CollaboraterException;
import com.elmiraouy.jwtsecurity.service.ClassificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/client/classification")
public class ClassificationController {
    private final ClassificationService classificationService;

    @GetMapping("/get")
    public ResponseEntity<ClassificationResponseDto> findByCollaborater(@RequestParam Long id) throws ClassificationException, CollaboraterException {
        return ResponseEntity.ok(classificationService.findByCollaborater(id));
    }

    @PostMapping("/add")
    public ResponseEntity<ClassificationResponseDto> addClassificationToCollaborater(@RequestBody ClassificationRequestDto classificationRequestDto) throws CollaboraterException, ClassificationTypeException {
        return ResponseEntity.ok(classificationService.addClassificationToCollaborater(classificationRequestDto));
    }

    @GetMapping("/getTypes")
    public ResponseEntity<List<ClassificationTypeResponseDto>> getTypes(){
        return ResponseEntity.ok(classificationService.getAllTypes());
    }
}
