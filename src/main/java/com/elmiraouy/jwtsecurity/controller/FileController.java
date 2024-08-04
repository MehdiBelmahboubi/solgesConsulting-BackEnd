package com.elmiraouy.jwtsecurity.controller;

import com.elmiraouy.jwtsecurity.Dto.response.ResponseDto;
import com.elmiraouy.jwtsecurity.repository.ContractRepository;
import com.elmiraouy.jwtsecurity.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class FileController {
    private final TypeUnitOrganisationalService typeUnitOrganisationalService;
    private final CollaboraterService collaboraterService;
    private final ContractService contractService;
    private final ClassificationService classificationService;

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("table") String table,@RequestParam("companyId") Long companyId,@RequestParam("userCreatedId") Long userCreatedId)  {
        ResponseDto responseDto = new ResponseDto();
        if (file == null || file.isEmpty()) {
            responseDto.setMessage("File n'est pas charger ");
            log.error("File n'est pas charger ");
        }
        else {
            String fileName = file.getOriginalFilename();
            if ( fileName != null && fileName.endsWith(".xlsx")) {
                try {
                    if(!table.isEmpty()){
                        if(table.equals("Type_Unity")){
                            typeUnitOrganisationalService.persistFromFile(file,table, companyId, userCreatedId);
                        } else if (table.equals("Collaborater")) {
                            collaboraterService.persistFromFile(file,table, companyId);
                        } else if (table.equals("Contract")) {
                            contractService.persistFromFile(file,table);
                        } else if (table.equals("Classification")) {
                            classificationService.persistFromFile(file,table);
                        }
                    }
                    responseDto.setMessage("Fichier  importer avec succes");
                    log.info("Fichier importer avec succes");
                    return ResponseEntity.ok(responseDto);
                } catch (Exception e) {
                    responseDto.setMessage("erreur dans l'import de fichier ");
                    log.info("erreur dans l'import de fichier ");
                    return ResponseEntity.ok(responseDto);
                }
            }
            else {
                log.error("format n'est pas validé .support Excel  (.xlsx) ");
                responseDto.setMessage("format n'est pas validé .support Excel  (.xlsx) ");
            }

        }
        return ResponseEntity.ok(responseDto);
    }

}
