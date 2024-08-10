package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.ClassificationRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.ClassificationResponseDto;
import com.elmiraouy.jwtsecurity.Dto.response.ClassificationTypeResponseDto;
import com.elmiraouy.jwtsecurity.entities.Classification;
import com.elmiraouy.jwtsecurity.entities.ClassificationType;
import com.elmiraouy.jwtsecurity.entities.Collaborater;
import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.handlerException.ClassificationException;
import com.elmiraouy.jwtsecurity.handlerException.ClassificationTypeException;
import com.elmiraouy.jwtsecurity.handlerException.CollaboraterException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.mappers.ClassificationDtoMapper;
import com.elmiraouy.jwtsecurity.mappers.ClassificationTypeDtoMapper;
import com.elmiraouy.jwtsecurity.repository.ClassificationRepository;
import com.elmiraouy.jwtsecurity.repository.ClassificationTypeRepository;
import com.elmiraouy.jwtsecurity.repository.CollaboraterRepository;
import com.elmiraouy.jwtsecurity.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ClassificationServiceImpl implements ClassificationService{
    private final ClassificationRepository classificationRepository;
    private final CollaboraterRepository collaboraterRepository;
    private final CompanyRepository companyRepository;
    private final ClassificationDtoMapper classificationDtoMapper;
    private final ClassificationTypeRepository classificationTypeRepository;
    private final ClassificationTypeDtoMapper classificationTypeDtoMapper;

    @Override
    public ClassificationResponseDto addClassificationToCollaborater(ClassificationRequestDto request) throws CollaboraterException, ClassificationTypeException, ClassificationException, CompanyException {
        if (request.getDateFin().isBefore(LocalDateTime.now())) {
            throw new ClassificationException("Date fin is before current date");
        } else if (request.getDateClassification().isAfter(request.getDateFin())) {
            throw new ClassificationException("Date Classification is after Date Fin");
        }

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new CompanyException("Company avec Id Introuvable: [%s] :".formatted(request.getCompanyId())));


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
                .addedInBulk(false)
                .dateCreation(LocalDateTime.now())
                .collaborater(collaborater)
                .classificationType(classificationType)
                .company(company)
                .build();
        classificationRepository.save(classification);
        return classificationDtoMapper.apply(classification);
    }

    @Override
    public ClassificationResponseDto updateClassification(ClassificationRequestDto request) throws CollaboraterException, ClassificationTypeException, ClassificationException {
        if (request.getDateFin().isBefore(LocalDateTime.now())) {
            throw new ClassificationException("Date fin is before current date");
        } else if (request.getDateClassification().isAfter(request.getDateFin())) {
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
        List<ClassificationType> classificationTypes = classificationTypeRepository.findAllByActiveIsTrue();
        return classificationTypes.stream().map(classificationTypeDtoMapper).toList();
    }

    @Override
    @Transactional
    public void persistFromFile(MultipartFile file, String table,Long companyId) {
        List<ClassificationRequestDto> classificationRequestDtos;
        try {
            InputStream is = file.getInputStream();
            classificationRequestDtos = excelToContracts(is, table,companyId);
            classificationRequestDtos.forEach(request -> {
                try {
                    saveInBulk(request);
                } catch (Exception e) {
                    throw new RuntimeException("Erreur lors de l'enregistrement du classification : " + e.getMessage(), e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("Erreur dans l'enregistrement des informations importer : " + e.getMessage());
        }
    }

    private List<ClassificationRequestDto> excelToContracts(InputStream is, String table,Long companyId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<ClassificationRequestDto> classificationRequestDtos = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(table);
            if (sheet == null) {
                throw new RuntimeException("Sheet named '" + table + "' does not exist.");
            }
            Iterator<Row> rows = sheet.iterator();
            int rowNumber = 0;
            Map<String, Integer> columnMap = new HashMap<>();
            if (!rows.hasNext()) {
                throw new RuntimeException("The file is empty, it must contain records...");
            }

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                if (rowNumber == 0) {
                    Iterator<Cell> header = currentRow.iterator();
                    int cellIdx = 0;
                    while (header.hasNext()) {
                        Cell cellule = header.next();
                        String columnName = cellule.getStringCellValue();
                        columnMap.put(columnName, cellIdx);
                        cellIdx++;
                    }
                    rowNumber++;
                    continue;
                }
                ClassificationRequestDto classificationRequestDto = ClassificationRequestDto.builder().build();
                classificationRequestDto.setCompanyId(companyId);
                if (columnMap.containsKey("ref_classification")) {
                    classificationRequestDto.setRefClassification(currentRow.getCell(columnMap.get("ref_classification")).getStringCellValue());
                }else {
                    throw new ClassificationException("La reference classification Not null.");
                }
                if (columnMap.containsKey("categorie_prof")) {
                    classificationRequestDto.setCategorieProf(currentRow.getCell(columnMap.get("categorie_prof")).getStringCellValue());
                }
                if (columnMap.containsKey("date_classification")) {
                    classificationRequestDto.setDateClassification(SharedService.handleDate(columnMap, currentRow, "date_classification", formatter));
                }
                if (columnMap.containsKey("date_categorie_prof")) {
                    classificationRequestDto.setDateCategorieProf(SharedService.handleDate(columnMap, currentRow, "date_categorie_prof", formatter));
                }
                if (columnMap.containsKey("date_fin")) {
                    classificationRequestDto.setDateFin(SharedService.handleDate(columnMap, currentRow, "date_fin", formatter));
                }
                if (columnMap.containsKey("classification_type")) {
                    Long classificationType = classificationTypeRepository.findByNom(currentRow.getCell(columnMap.get("classification_type")).getStringCellValue());
                    classificationRequestDto.setClassificationType(classificationType);
                }
                if (columnMap.containsKey("matricule")) {
                    Long collaboraterId = collaboraterRepository.findByMatricule(currentRow.getCell(columnMap.get("matricule")).getStringCellValue());
                    classificationRequestDto.setCollaboraterId(collaboraterId);
                }
                classificationRequestDtos.add(classificationRequestDto);
            }
            workbook.close();
            return classificationRequestDtos;
        } catch (IOException | ClassificationException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveInBulk(ClassificationRequestDto request) throws ClassificationTypeException, CollaboraterException, ClassificationException, CompanyException {
        if(request.getDateFin() != null){
            if (request.getDateFin().isBefore(LocalDateTime.now())) {
                throw new ClassificationException("Date fin is before current date");
            } else if (request.getDateClassification().isAfter(request.getDateFin())) {
                throw new ClassificationException("Date Classification is after Date Fin");
            }
        }
        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new CompanyException("Company avec Id Introuvable: [%s] :".formatted(request.getCompanyId())));
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
                .addedInBulk(true)
                .dateCreation(LocalDateTime.now())
                .collaborater(collaborater)
                .classificationType(classificationType)
                .company(company)
                .build();
        classificationRepository.save(classification);
    }
}
