package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.ContractRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.ContractResponseDto;
import com.elmiraouy.jwtsecurity.Dto.response.ContractTypeResponseDTO;
import com.elmiraouy.jwtsecurity.entities.Collaborater;
import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.entities.Contract;
import com.elmiraouy.jwtsecurity.entities.ContractType;
import com.elmiraouy.jwtsecurity.handlerException.CollaboraterException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.ContractException;
import com.elmiraouy.jwtsecurity.handlerException.ContractTypeException;
import com.elmiraouy.jwtsecurity.mappers.ContractDtoMapper;
import com.elmiraouy.jwtsecurity.mappers.ContractTypeDtoMapper;
import com.elmiraouy.jwtsecurity.repository.CollaboraterRepository;
import com.elmiraouy.jwtsecurity.repository.CompanyRepository;
import com.elmiraouy.jwtsecurity.repository.ContractRepository;
import com.elmiraouy.jwtsecurity.repository.ContractTypeRepository;
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
public class ContractServiceImpl implements ContractService{
    private final ContractRepository contractRepository;
    private final CollaboraterRepository collaboraterRepository;
    private final ContractDtoMapper contractDtoMapper;
    private final ContractTypeRepository contractTypeRepository;
    private final ContractTypeDtoMapper contractTypeDtoMapper;
    private final CompanyRepository companyRepository;

    @Override
    public ContractResponseDto addContractToCollaborator(ContractRequestDto request) throws CollaboraterException, ContractTypeException, ContractException, CompanyException {
        if (request.getDateFin().isBefore(LocalDateTime.now())) {
            throw new ContractException("Date fin is before current date");
        } else if (request.getDateEntree().isAfter(request.getDateFin())) {
            throw new ContractException("Date Entree is after Date Fin");
        }

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new CompanyException("Company avec Id Introuvable: [%s] :".formatted(request.getCompanyId())));

        Collaborater collaborater = collaboraterRepository.findById(request.getCollaboraterId())
                .orElseThrow(() -> new CollaboraterException("Collaborater avec Id Introuvable: [%s]".formatted(request.getCollaboraterId())));

        Contract activeContract = contractRepository.findByCollaboraterAndActive(collaborater, true);
        if (activeContract != null) {
            activeContract.setActive(false);
            contractRepository.save(activeContract);
        }

        ContractType contractType = contractTypeRepository.findById(request.getContractType())
                .orElseThrow(() -> new ContractTypeException("ContractType avec Id Introuvable: [%s]".formatted(request.getContractType())));

        Contract newContract = Contract.builder()
                .contractRef(request.getContractRef())
                .motifRecrutement(request.getMotifRecrutement())
                .dateEntree(request.getDateEntree())
                .periodNegocible(request.getPeriodNegocible())
                .regimeFiscal(request.getRegimeFiscal())
                .exonerationFiscale(request.getExonerationFiscale())
                .motifDepart(request.getMotifDepart())
                .dateFin(request.getDateFin())
                .active(true)
                .addedInBulk(false)
                .dateCreation(LocalDateTime.now())
                .collaborater(collaborater)
                .contractType(contractType)
                .company(company)
                .build();

        contractRepository.save(newContract);
        return contractDtoMapper.apply(newContract);
    }


    @Override
    public ContractResponseDto updateContract(ContractRequestDto request) throws CollaboraterException, ContractTypeException, ContractException {
        if (request.getDateFin().isBefore(LocalDateTime.now())) {
            throw new ContractException("Date fin is before current date");
        } else if (request.getDateEntree().isAfter(request.getDateFin())) {
            throw new ContractException("Date Entree is after Date Fin");
        }
        Contract contract = contractRepository.findById(request.getId())
                .orElseThrow(()->new ContractException("Contract with this Id Introuvable: [%s] :".formatted(request.getId())));
        Collaborater collaborater = collaboraterRepository.findById(request.getCollaboraterId())
                .orElseThrow(() -> new CollaboraterException("Collaborater avec Id Introuvable: [%s]".formatted(request.getCollaboraterId())));
        ContractType contractType = contractTypeRepository.findById(request.getContractType())
                .orElseThrow(() -> new ContractTypeException("ContractType avec Id Introuvable: [%s]".formatted(request.getContractType())));
        contract.setContractRef(request.getContractRef());
        contract.setMotifRecrutement(request.getMotifRecrutement());
        contract.setDateEntree(request.getDateEntree());
        contract.setPeriodNegocible(request.getPeriodNegocible());
        contract.setRegimeFiscal(request.getRegimeFiscal());
        contract.setExonerationFiscale(request.getExonerationFiscale());
        contract.setMotifDepart(request.getMotifDepart());
        contract.setDateFin(request.getDateFin());
        contract.setCollaborater(collaborater);
        contract.setContractType(contractType);
        contractRepository.save(contract);
        return contractDtoMapper.apply(contract);
    }

    @Override
    public ContractResponseDto deleteContractToCollaborater(ContractRequestDto contractRequestDto) throws CollaboraterException, ContractException {
        return null;
    }

    @Override
    public List<ContractTypeResponseDTO> getAllTypes() {
        List<ContractType> contractTypes = contractTypeRepository.findAllByActiveIsTrue();
        return contractTypes.stream().map(contractTypeDtoMapper).toList();
    }

    @Override
    @Transactional
    public void persistFromFile(MultipartFile file, String table, Long companyId) {
        List<ContractRequestDto> contractRequestDtos;
        try {
            InputStream is = file.getInputStream();
            contractRequestDtos = excelToContracts(is, table, companyId);
            contractRequestDtos.forEach(request -> {
                try {
                    saveInBulk(request);
                } catch (Exception e) {
                    throw new RuntimeException("Erreur lors de l'enregistrement du contrat : " + e.getMessage(), e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("Erreur dans l'enregistrement des informations importer : " + e.getMessage());
        }
    }

    private List<ContractRequestDto> excelToContracts(InputStream is, String table, Long companyId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<ContractRequestDto> contractRequestDtos = new ArrayList<>();
        Workbook workbook = null;
        try{
            workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(table);
            if(sheet == null){
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
                ContractRequestDto contractRequestDto = ContractRequestDto.builder().build();
                contractRequestDto.setCompanyId(companyId);
                if (columnMap.containsKey("contract_ref")) {
                    contractRequestDto.setContractRef(currentRow.getCell(columnMap.get("contract_ref")).getStringCellValue());
                }else {
                    throw new ContractException("La reference contract Not null.");
                }
                if (columnMap.containsKey("motif_recrutement")) {
                    contractRequestDto.setMotifRecrutement(currentRow.getCell(columnMap.get("motif_recrutement")).getStringCellValue());
                }
                if (columnMap.containsKey("date_entree")) {
                    contractRequestDto.setDateEntree(SharedService.handleDate(columnMap, currentRow, "date_entree", formatter));
                }
                if (columnMap.containsKey("period_negocible")) {
                    contractRequestDto.setPeriodNegocible((int) currentRow.getCell(columnMap.get("period_negocible")).getNumericCellValue());
                }
                if (columnMap.containsKey("regime_fiscal")) {
                    contractRequestDto.setRegimeFiscal(currentRow.getCell(columnMap.get("regime_fiscal")).getStringCellValue());
                }
                if (columnMap.containsKey("exoneration_fiscale")) {
                    contractRequestDto.setExonerationFiscale((int) currentRow.getCell(columnMap.get("exoneration_fiscale")).getNumericCellValue());
                }
                if (columnMap.containsKey("motif_depart")) {
                    contractRequestDto.setMotifDepart(currentRow.getCell(columnMap.get("motif_depart")).getStringCellValue());
                }
                if (columnMap.containsKey("date_fin")) {
                    contractRequestDto.setDateFin(SharedService.handleDate(columnMap, currentRow, "date_fin", formatter));
                }
                if (columnMap.containsKey("type_contrat")) {
                    Long contractType = contractTypeRepository.findByCode(currentRow.getCell(columnMap.get("type_contrat")).getStringCellValue());
                    contractRequestDto.setContractType(contractType);
                }
                if (columnMap.containsKey("matricule")) {
                    Long collaboraterId = collaboraterRepository.findByMatricule(currentRow.getCell(columnMap.get("matricule")).getStringCellValue());
                    contractRequestDto.setCollaboraterId(collaboraterId);
                }
                contractRequestDtos.add(contractRequestDto);
            }
            workbook.close();
            return contractRequestDtos;
        } catch (IOException e) {
            throw new RuntimeException("Erreur Au moment de lecture du fichier verifier les informations : " + e.getMessage());
        } catch (ContractException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveInBulk(ContractRequestDto request) throws CollaboraterException, ContractTypeException, ContractException, CompanyException {
        if(request.getDateFin() != null){
            if (request.getDateFin().isBefore(LocalDateTime.now())) {
                throw new ContractException("Date fin is before current date");
            } else if (request.getDateEntree().isAfter(request.getDateFin())) {
                throw new ContractException("Date Entree is after Date Fin");
            }
        }
        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new CompanyException("Company avec Id Introuvable: [%s] :".formatted(request.getCompanyId())));
        Collaborater collaborater = collaboraterRepository.findById(request.getCollaboraterId())
                .orElseThrow(() -> new CollaboraterException("Collaborater avec Id Introuvable: [%s]".formatted(request.getCollaboraterId())));
        Contract activeContract = contractRepository.findByCollaboraterAndActive(collaborater, true);
        if (activeContract != null) {
            activeContract.setActive(false);
            contractRepository.save(activeContract);
        }
        ContractType contractType = contractTypeRepository.findById(request.getContractType())
                .orElseThrow(() -> new ContractTypeException("ContractType avec Id Introuvable: [%s]".formatted(request.getContractType())));
        Contract newContract = Contract.builder()
                .contractRef(request.getContractRef())
                .motifRecrutement(request.getMotifRecrutement())
                .dateEntree(request.getDateEntree())
                .periodNegocible(request.getPeriodNegocible())
                .regimeFiscal(request.getRegimeFiscal())
                .exonerationFiscale(request.getExonerationFiscale())
                .motifDepart(request.getMotifDepart())
                .dateFin(request.getDateFin())
                .active(true)
                .addedInBulk(true)
                .dateCreation(LocalDateTime.now())
                .collaborater(collaborater)
                .contractType(contractType)
                .company(company)
                .build();

        contractRepository.save(newContract);
    }
}
