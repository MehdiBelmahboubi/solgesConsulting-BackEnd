package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.TypeUnitOrganisationalRequest;
import com.elmiraouy.jwtsecurity.Dto.response.TypeUnitOrganisationalResponseDto;
import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.entities.TypeUnitOrganisational;
import com.elmiraouy.jwtsecurity.entities.UnitOrganisational;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.EntityNotFoundException;
import com.elmiraouy.jwtsecurity.handlerException.TypeUnityException;
import com.elmiraouy.jwtsecurity.mappers.TypeUnitOrganisationalDtoMapper;
import com.elmiraouy.jwtsecurity.repository.CompanyRepository;
import com.elmiraouy.jwtsecurity.repository.TypeUnitOrganisationalRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class TypeUnitOrganisationalServiceImpl implements TypeUnitOrganisationalService{

    private final TypeUnitOrganisationalRepository typeUnitOrganisationalRepository;
    private final TypeUnitOrganisationalDtoMapper typeUnitOrganisationalDtoMapper;
    private final CompanyRepository companyRepository;

    @Transactional
        @Override
    public TypeUnitOrganisationalResponseDto findById(Long id) throws EntityNotFoundException {
        return null;
    }
    @Transactional
    @Override
    public List<TypeUnitOrganisationalResponseDto> getAll(Long companyId) throws EntityNotFoundException {
        List<TypeUnitOrganisationalResponseDto> typeUnitOrganisationalList=typeUnitOrganisationalRepository.findByCompanyId(companyId);
        if(typeUnitOrganisationalList.isEmpty()) {
            return new ArrayList<>();
        }
        return typeUnitOrganisationalList;
    }

    @Override
    public TypeUnitOrganisationalResponseDto findByNameOrCode(String criteria) throws EntityNotFoundException {
        return null;
    }

    @Override
    public List<TypeUnitOrganisationalResponseDto> findByCompanyId(Long companyId) throws EntityNotFoundException {
          List<TypeUnitOrganisational> typeUnitOrganisationalList=typeUnitOrganisationalRepository.findAll();
          return typeUnitOrganisationalList.stream().map(typeUnitOrganisationalDtoMapper).toList();
    }
    @Transactional
    @Override
    public void persistFromFile(MultipartFile file, String table, Long companyId, Long userCreatedId) {
        List<TypeUnitOrganisationalRequest> types=null;
        //List<TypeUnitOrganisationalResponseDto> typesSave=new ArrayList<>();
        try {
            types =excelToTypesUnity(file.getInputStream(),table, companyId, userCreatedId);

            types.forEach(type-> {
                try {
                    //TypeUnitOrganisationalResponseDto save1 = save(type);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("Erreur dans l'enregistrement des informations importer : " + e.getMessage());
        }
    }
    public static List<TypeUnitOrganisationalRequest> excelToTypesUnity(InputStream is, String table, Long companyId, Long userCreatedId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int cpt=0;
        try {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheet(table);
            System.out.println("name of sheet :"+sheet.getSheetName());
            Iterator<Row> rows = sheet.iterator();
            List<TypeUnitOrganisationalRequest> typeUnitOrganisationalRequestsDtos = new ArrayList<>();
            int rowNumber = 0;
            Map<String, Integer> columnMap = new HashMap<>();
            if (!rows.hasNext())
                throw new RuntimeException("Le fichier est vide, il doit contenir des enregistrements...");
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
                TypeUnitOrganisationalRequest typeUnity = TypeUnitOrganisationalRequest.builder().build();
                if (columnMap.containsKey("name") ) {
                    System.out.println("name "+cpt+":"+currentRow.getCell(columnMap.get("name")).getStringCellValue());
                    typeUnity.setName(String.valueOf(currentRow.getCell(columnMap.get("name")).getStringCellValue()));
                }
                else {
                    throw new TypeUnityException("Le nom est obligatoires.");
                }
                if (columnMap.containsKey("code")) {
                    System.out.println("code "+cpt+":"+currentRow.getCell(columnMap.get("code")).getStringCellValue());
                    typeUnity.setCode(String.valueOf(currentRow.getCell(columnMap.get("code")).getStringCellValue()));
                } else {
                    throw new TypeUnityException("Le code est obligatoires.");
                }

                if (columnMap.containsKey("color")) {
                    System.out.println("color "+cpt+":"+currentRow.getCell(columnMap.get("color")).getStringCellValue());
                    typeUnity.setColor(String.valueOf(currentRow.getCell(columnMap.get("color")).getStringCellValue()));
                }
                if (columnMap.containsKey("level")) {
                    System.out.println("level "+cpt+":"+currentRow.getCell(columnMap.get("level")));
                    System.out.println("type column: "+currentRow.getCell(columnMap.get("level")).getCellType());
                    typeUnity.setLevel((int) currentRow.getCell(columnMap.get("level")).getNumericCellValue());
                }

                typeUnity.setCompanyId(companyId);
                typeUnity.setIdUserCreated(userCreatedId);
                if (columnMap.containsKey("active")) {
                    System.out.println("active "+cpt+":"+currentRow.getCell(columnMap.get("active")).getStringCellValue());
                    typeUnity.setActive(Boolean.valueOf(currentRow.getCell(columnMap.get("active")).getStringCellValue()));
                }
                //transfere des dates
                //.setCreateDate( SharedService.handleDate(columnMap, currentRow, "create_date", formatter));
                //unityRequest.setDecisionDate( SharedService.handleDate(columnMap, currentRow, "decision_date", formatter));

                typeUnity.setAddedInBulk(true);
                typeUnitOrganisationalRequestsDtos.add(typeUnity);
            }
            workbook.close();
            return typeUnitOrganisationalRequestsDtos;
        } catch (IOException e) {
            throw new RuntimeException("erreur Au moment de lecture du fichier verifier les informations : " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
