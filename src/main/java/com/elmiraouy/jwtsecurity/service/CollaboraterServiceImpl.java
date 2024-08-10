package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.CollaboraterRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.*;
import com.elmiraouy.jwtsecurity.entities.*;
import com.elmiraouy.jwtsecurity.enums.Civilite;
import com.elmiraouy.jwtsecurity.enums.Sexe;
import com.elmiraouy.jwtsecurity.handlerException.*;
import com.elmiraouy.jwtsecurity.mappers.CollaboraterDtoMapper;
import com.elmiraouy.jwtsecurity.mappers.ContractTypeDtoMapper;
import com.elmiraouy.jwtsecurity.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CollaboraterServiceImpl implements CollaboraterService{
    private final CollaboraterRepository collaboraterRepository;
    private final CompanyRepository companyRepository;
    private final CollaboraterDtoMapper collaboraterDtoMapper;
    private final CountryRepository countryRepository;
    private final ContractRepository contractRepository;
    private final ClassificationRepository classificationRepository;
    @Override
    public Page<CollaboraterResponseDto> findByCompany(Long companyId, int page, int size) throws CompanyException {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<CollaboraterResponseDto> collaboratersPage = collaboraterRepository.findAllByCompanyAndActive(companyId, pageable);

        if (collaboratersPage.isEmpty()) {
            return Page.empty();
        }
        return collaboratersPage;
    }

    @Override
    public Page<CollaboraterResponseDto> findArchivedByCompany(Long companyId, int page, int size) throws CompanyException {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<CollaboraterResponseDto> collaboratersPage = collaboraterRepository.findByCompanyAndActive(companyId, pageable);

        if (collaboratersPage.isEmpty()) {
            return Page.empty();
        }
        return collaboratersPage;
    }

    @Override
    public CollaboraterResponseDto findById(Long id) throws CollaboraterException,ClassificationException {
        Collaborater collaborater = collaboraterRepository.findById(id)
                .orElseThrow(() -> new CollaboraterException(String.format("Collaborater with this Id Introuvable: [%s]", id)));

        CollaboraterResponseDto collaboraterResponseDto = collaboraterDtoMapper.apply(collaborater);

        Optional<ContractResponseDto> contractOptional = contractRepository.findByCollaboraterAndActive(collaborater);
        contractOptional.ifPresentOrElse(
                collaboraterResponseDto::setContract,
                () -> collaboraterResponseDto.setContract(null)
        );
        Date currentDate = new Date();
        Optional<ClassificationResponseDto> classificationOptional = classificationRepository.findByCollaboraterAndActive(collaborater);
        classificationOptional.ifPresentOrElse(
                collaboraterResponseDto::setClassification,
                () -> collaboraterResponseDto.setClassification(null)
        );

        List<CountryResponseDto> countries = collaborater.getCountries().stream()
                .map(country -> new CountryResponseDto(country.getCode(), country.getNationality()))
                .collect(Collectors.toList());
        collaboraterResponseDto.setCountries(countries);
        return collaboraterResponseDto;
    }


    @Override
    public CollaboraterResponseDto createCollab(CollaboraterRequestDto request) throws CollaboraterException, CompanyException, CountryException {
          if(collaboraterRepository.findByMatriculeAndCompanyIdOrCnie(request.getMatricule(), request.getCompanyId(),request.getCnie()).isPresent()) {
            throw new CollaboraterException("un collaborateur avec ce Matricule deja creer dans cette company: [%s] :".formatted(request.getMatricule()));}
        Collaborater collaborater = buildCollaborater(request);
        Collaborater saveCollaborater = collaboraterRepository.save(collaborater);
        addNationalitiesToCollaborater(saveCollaborater, request.getCountryCode1(), request.getCountryCode2());
        return collaboraterDtoMapper.apply(saveCollaborater);
    }

    public Collaborater buildCollaborater(CollaboraterRequestDto request) throws CollaboraterException, CompanyException {
        Civilite civilite = request.getSexe() == Sexe.Homme ? Civilite.Mr : Civilite.Mme;
        LocalDateTime dateNaissance = request.getDateNaissance();
        if (dateNaissance.isAfter(LocalDateTime.now())) {
            throw new CollaboraterException("Date Naissance Error: " + dateNaissance);
        }
        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new CompanyException("Company avec Id Introuvable: [%s] :".formatted(request.getCompanyId())));
        return Collaborater.builder()
                .matricule(request.getMatricule())
                .civilite(civilite)
                .initiales(request.getInitiales())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateNaissance(request.getDateNaissance())
                .lieuNaissance(request.getLieuNaissance())
                .sexe(request.getSexe())
                .civNomPrenom(request.getFirstName() + " " + request.getLastName())
                .civPrenomNom(request.getLastName() + " " + request.getFirstName())
                .dateCreation(LocalDateTime.now())
                .active(true)
                .addedInBulk(false)
                .company(company)
                .countries(new ArrayList<>())
                .build();
    }

    @Override
    public void addNationalitiesToCollaborater(Collaborater collaborater, Long countryCode1, Long countryCode2) throws CountryException {
        if (countryCode1 != null) {
            Country country1 = countryRepository.findById(countryCode1)
                    .orElseThrow(() -> new CountryException("Country not found with code: " + countryCode1));
            collaborater.getCountries().add(country1);
            country1.getCollaborators().add(collaborater);
            countryRepository.save(country1);
        }
        if (countryCode2 != null) {
            Country country2 = countryRepository.findById(countryCode2)
                    .orElseThrow(() -> new CountryException("Country not found with code: " + countryCode2));
            collaborater.getCountries().add(country2);
            country2.getCollaborators().add(collaborater);
            countryRepository.save(country2);
        }
        System.out.println("nationa added to collaborater : "+collaborater.getCivNomPrenom());
    }

    @Override
    public CollaboraterResponseDto deleteCollab(Long id) throws CollaboraterException {
        Collaborater collaborater = collaboraterRepository.findById(id)
                .orElseThrow(() -> new CollaboraterException("Collaborater with this Id Introuvable: [%s] :".formatted(id)));
        collaborater.setActive(false);
        collaboraterRepository.save(collaborater);
        return collaboraterDtoMapper.apply(collaborater);
    }

    @Override
    public CollaboraterResponseDto updateCollab( CollaboraterRequestDto request) throws CollaboraterException {
        Collaborater collaborater = collaboraterRepository.findById(request.getId())
                .orElseThrow(() -> new CollaboraterException("Collaborater with this Id Introuvable: [%s] :".formatted(request.getId())));
        Civilite civilite = request.getSexe() == Sexe.Homme ? Civilite.Mr : Civilite.Mme;
        LocalDateTime dateNaissance = request.getDateNaissance();
        if (dateNaissance.isAfter(LocalDateTime.now())) {
            throw new CollaboraterException("Date Naissance Error: " + dateNaissance);
        }
        if(!Objects.equals(request.getCnie(), collaborater.getCnie())){
            throw new CollaboraterException("Impossible de changer Cnie: [%s] :".formatted(request.getCnie()));
        }
        if(collaboraterRepository.findByMatriculeAndCompanyId(request.getMatricule(), request.getCompanyId()).isPresent()) {
            throw new CollaboraterException("un collaborateur avec ce Matricule deja creer dans cette company: [%s] :".formatted(request.getMatricule()));
        }
        collaborater.setMatricule(request.getMatricule());
        collaborater.setCivilite(civilite);
        collaborater.setInitiales(request.getInitiales());
        collaborater.setFirstName(request.getFirstName());
        collaborater.setLastName(request.getLastName());
        collaborater.setDateNaissance(request.getDateNaissance());
        collaborater.setLieuNaissance(request.getLieuNaissance());
        collaborater.setSexe(request.getSexe());
        collaborater.setCivNomPrenom(request.getFirstName() + " " + request.getLastName());
        collaborater.setCivPrenomNom(request.getLastName() + " " + request.getFirstName());
        collaborater.setCnie(request.getCnie());
        collaborater.setCnieDelivreeLe(request.getCnieDelivreeLe());
        collaborater.setCnieDelivreePar(request.getCnieDelivreePar());
        collaborater.setCnieExpireLe(request.getCnieExpireLe());
        collaborater.setNatPermisSejour(request.getNatPermisSejour());
        collaborater.setPermisSejourDelivreLe(request.getPermisSejourDelivreLe());
        collaborater.setPermisSejourDebVal(request.getPermisSejourDebVal());
        collaborater.setPermisSejourFinVal(request.getPermisSejourFinVal());
        collaborater.setNumPermisTravail(request.getNumPermisTravail());
        collaborater.setNatPermisTravail(request.getNatPermisTravail());
        collaborater.setPermisTravailDelivreLe(request.getPermisTravailDelivreLe());
        collaborater.setPermisTravailDebVal(request.getPermisTravailDebVal());
        collaborater.setPermisTravailFinVal(request.getPermisTravailFinVal());
        collaborater.setNumPassePort(request.getNumPassePort());
        collaborater.setPassePortDelivreLe(request.getPassePortDelivreLe());
        collaborater.setPassePortDelivrePar(request.getPassePortDelivrePar());
        collaborater.setPassePortExpireLe(request.getPassePortExpireLe());
        collaborater.setTelephone(request.getTelephone());
        collaborater.setTel1(request.getTel1());
        collaborater.setTel2(request.getTel2());
        collaborater.setTel3(request.getTel3());
        collaborater.setEmail1(request.getEmail1());
        collaborater.setEmail2(request.getEmail2());
        collaborater.setEmail3(request.getEmail3());
        collaborater.setAdresse1(request.getAdresse1());
        collaborater.setAdresse2(request.getAdresse2());
        collaborater.setAdresse3(request.getAdresse3());
        if(request.getNbEnfantsSaisi()==false)
        {
            collaborater.setNbEnfants(null);
        }else {
            collaborater.setNbEnfants(request.getNbEnfants());
        }
        collaborater.setNbEnfantsSaisi(request.getNbEnfantsSaisi());
        if(!request.getNbEnfantsChargeSaisi())
        {
            collaborater.setNbEnfantCharge(null);
        }else {
            collaborater.setNbEnfantCharge(request.getNbEnfantCharge());
        }
        collaborater.setNbEnfantsChargeSaisi(request.getNbEnfantsChargeSaisi());
        collaborater.setNbPersCharge(request.getNbPersCharge());
        collaborater.setNomJeuneFille(request.getNomJeuneFille());
        if(!request.getNbEpousesSaisi())
        {
            collaborater.setNbEpouses(null);
        }else {
            collaborater.setNbEpouses(request.getNbEpouses());
        }
        collaborater.setNbEpousesSaisi(request.getNbEpousesSaisi());
        collaborater.setDateNaturalisation(request.getDateNaturalisation());
        collaborater.setActive(request.getActive());
        collaborater.setRecrutable(request.getRecrutable());
        collaborater.setExcluDeclaration(request.getExcluDeclaration());
        collaborater.setMatriculeRecrutement(request.getMatriculeRecrutement());
        collaborater.setDateUpdate(LocalDateTime.now());

        collaboraterRepository.save(collaborater);

        return collaboraterDtoMapper.apply(collaborater);
    }

    @Override
    @Transactional
    public void persistFromFile(MultipartFile file, String table, Long companyId) {
        List<CollaboraterRequestDto> collaboraterRequests;
        try {
            System.out.println("File Name: " + file.getOriginalFilename());
            System.out.println("File Size: " + file.getSize());

            InputStream is = file.getInputStream();
            System.out.println("InputStream available bytes: " + is.available());
            collaboraterRequests = excelToCollaboraters(is, table, companyId);
            collaboraterRequests.forEach(request -> {
                try {
                    saveInBulk(request);
                } catch (Exception e) {
                    throw new RuntimeException("Erreur lors de l'enregistrement du collaborateur : " + e.getMessage(), e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("Erreur dans l'enregistrement des informations importer : " + e.getMessage());
        }
    }

    public List<CollaboraterRequestDto> excelToCollaboraters(InputStream is, String table, Long companyId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<CollaboraterRequestDto> collaboraterRequests = new ArrayList<>();
        Workbook workbook = null;

        try {
            System.out.println("Attempting to create workbook from input stream.");
//            workbook = WorkbookFactory.create(is);
            workbook = new XSSFWorkbook(is);
            System.out.println("Workbook created successfully.");

            Sheet sheet = workbook.getSheet(table);
            if (sheet == null) {
                throw new RuntimeException("Sheet named '" + table + "' does not exist.");
            }
            System.out.println("Processing Sheet: " + sheet.getSheetName());

            Iterator<Row> rows = sheet.iterator();
            System.out.println("up1");
            int rowNumber = 0;
            Map<String, Integer> columnMap = new HashMap<>();
            if (!rows.hasNext()) {
                throw new RuntimeException("The file is empty, it must contain records...");
            }

            while (rows.hasNext()) {
                System.out.println("up2");
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
                System.out.println("up3");

                CollaboraterRequestDto collaboraterRequest = CollaboraterRequestDto.builder().build();
                collaboraterRequest.setCompanyId(companyId);
                System.out.println("up4");

                if (columnMap.containsKey("matricule")) {
                    collaboraterRequest.setMatricule(currentRow.getCell(columnMap.get("matricule")).getStringCellValue());
                }else {
                        throw new CollaboraterException("Le matricule Not null.");
                }
                System.out.println("up5");

                if (columnMap.containsKey("civilite")) {
                    collaboraterRequest.setCivilite(Civilite.valueOf(currentRow.getCell(columnMap.get("civilite")).getStringCellValue()));
                }else {
                    throw new CollaboraterException("Le civilite Not null.");
                }
                System.out.println("up6");

                if (columnMap.containsKey("initiales")) {
                    collaboraterRequest.setInitiales(currentRow.getCell(columnMap.get("initiales")).getStringCellValue());
                }else {
                    throw new CollaboraterException("Le initiales Not null.");
                }
                System.out.println("up7");

                if (columnMap.containsKey("first_name")) {
                    collaboraterRequest.setFirstName(currentRow.getCell(columnMap.get("first_name")).getStringCellValue());
                }else {
                    throw new CollaboraterException("firstName Not null.");
                }
                System.out.println("up8");

                if (columnMap.containsKey("last_name")) {
                    collaboraterRequest.setLastName(currentRow.getCell(columnMap.get("last_name")).getStringCellValue());
                }else {
                    throw new CollaboraterException("lastName Not null.");
                }
                System.out.println("up9");

                if (columnMap.containsKey("sexe")) {
                    collaboraterRequest.setSexe(Sexe.valueOf(currentRow.getCell(columnMap.get("sexe")).getStringCellValue()));
                }else {
                    throw new CollaboraterException("sexe Not null.");
                }
                System.out.println("up10");

                if (columnMap.containsKey("date_naissance")) {
                    collaboraterRequest.setDateNaissance( SharedService.handleDate(columnMap, currentRow, "date_naissance", formatter));
                }else {
                    throw new CollaboraterException("dateNaissance Not null.");
                }
                System.out.println("up11");

                if (columnMap.containsKey("lieu_naissance")) {
                    collaboraterRequest.setLieuNaissance(currentRow.getCell(columnMap.get("lieu_naissance")).getStringCellValue());
                }else {
                    throw new CollaboraterException("lieu Naissance Not null.");
                }

                if (columnMap.containsKey("nom_jeune_fille")) {
                    collaboraterRequest.setNomJeuneFille(currentRow.getCell(columnMap.get("nom_jeune_fille")).getStringCellValue());
                }

                if (columnMap.containsKey("cnie")) {
                    collaboraterRequest.setCnie(currentRow.getCell(columnMap.get("cnie")).getStringCellValue());
                }else {
                    throw new CollaboraterException("cnie Not null.");
                }

                if (columnMap.containsKey("cnie_delivree_le")) {
                    collaboraterRequest.setCnieDelivreeLe(SharedService.handleDate(columnMap, currentRow, "cnie_delivree_le", formatter));
                }

                if (columnMap.containsKey("cnie_expire_le")) {
                    collaboraterRequest.setCnieExpireLe(SharedService.handleDate(columnMap, currentRow, "cnie_expire_le", formatter));
                }

                if (columnMap.containsKey("cnie_delivree_par")) {
                    collaboraterRequest.setCnieDelivreePar(currentRow.getCell(columnMap.get("cnie_delivree_par")).getStringCellValue());
                }

                if (columnMap.containsKey("num_permis_sejour")) {
                    collaboraterRequest.setNumPermisSejour(currentRow.getCell(columnMap.get("num_permis_sejour")).getStringCellValue());
                }

                if (columnMap.containsKey("nat_permis_sejour")) {
                    collaboraterRequest.setNatPermisSejour(currentRow.getCell(columnMap.get("nat_permis_sejour")).getStringCellValue());
                }

                if (columnMap.containsKey("permis_sejour_delivre_le")) {
                    collaboraterRequest.setPermisSejourDelivreLe(SharedService.handleDate(columnMap, currentRow, "permis_sejour_delivre_le", formatter));
                }

                if (columnMap.containsKey("permis_sejour_deb_val")) {
                    collaboraterRequest.setPermisSejourDebVal(SharedService.handleDate(columnMap, currentRow, "permis_sejour_deb_val", formatter));
                }

                if (columnMap.containsKey("permis_sejour_fin_val")) {
                    collaboraterRequest.setPermisSejourFinVal(SharedService.handleDate(columnMap, currentRow, "permis_sejour_fin_val", formatter));
                }

                if (columnMap.containsKey("num_permis_travail")) {
                    collaboraterRequest.setNumPermisTravail(currentRow.getCell(columnMap.get("num_permis_travail")).getStringCellValue());
                }

                if (columnMap.containsKey("nat_permis_travail")) {
                    collaboraterRequest.setNatPermisTravail(currentRow.getCell(columnMap.get("nat_permis_travail")).getStringCellValue());
                }

                if (columnMap.containsKey("permis_travail_delivre_le")) {
                    collaboraterRequest.setPermisTravailDelivreLe(SharedService.handleDate(columnMap, currentRow, "permis_travail_delivre_le", formatter));
                }

                if (columnMap.containsKey("permis_travail_deb_val")) {
                    collaboraterRequest.setPermisTravailDebVal(SharedService.handleDate(columnMap, currentRow, "permis_travail_deb_val", formatter));
                }

                if (columnMap.containsKey("permis_travail_fin_val")) {
                    collaboraterRequest.setPermisTravailFinVal(SharedService.handleDate(columnMap, currentRow, "permis_travail_fin_val", formatter));
                }

                if (columnMap.containsKey("num_passeport")) {
                    collaboraterRequest.setNumPassePort(currentRow.getCell(columnMap.get("num_passeport")).getStringCellValue());
                }

                if (columnMap.containsKey("passeport_delivre_le")) {
                    collaboraterRequest.setPassePortDelivreLe(SharedService.handleDate(columnMap, currentRow, "passeport_delivre_le", formatter));
                }

                if (columnMap.containsKey("passeport_expire_le")) {
                    collaboraterRequest.setPassePortExpireLe(SharedService.handleDate(columnMap, currentRow, "passeport_expire_le", formatter));
                }

                if (columnMap.containsKey("passeport_delivre_par")) {
                    collaboraterRequest.setPassePortDelivrePar(currentRow.getCell(columnMap.get("passeport_delivre_par")).getStringCellValue());
                }

                if (columnMap.containsKey("telephone")) {
                    collaboraterRequest.setTelephone(currentRow.getCell(columnMap.get("telephone")).getStringCellValue());
                }

                if (columnMap.containsKey("tel1")) {
                    collaboraterRequest.setTel1(currentRow.getCell(columnMap.get("tel1")).getStringCellValue());
                }

                if (columnMap.containsKey("tel2")) {
                    collaboraterRequest.setTel2(currentRow.getCell(columnMap.get("tel2")).getStringCellValue());
                }

                if (columnMap.containsKey("tel3")) {
                    collaboraterRequest.setTel3(currentRow.getCell(columnMap.get("tel3")).getStringCellValue());
                }

                if (columnMap.containsKey("email1")) {
                    collaboraterRequest.setEmail1(currentRow.getCell(columnMap.get("email1")).getStringCellValue());
                }

                if (columnMap.containsKey("email2")) {
                    collaboraterRequest.setEmail2(currentRow.getCell(columnMap.get("email2")).getStringCellValue());
                }

                if (columnMap.containsKey("email3")) {
                    collaboraterRequest.setEmail3(currentRow.getCell(columnMap.get("email3")).getStringCellValue());
                }

                if (columnMap.containsKey("adresse1")) {
                    collaboraterRequest.setAdresse1(currentRow.getCell(columnMap.get("adresse1")).getStringCellValue());
                }

                if (columnMap.containsKey("adresse2")) {
                    collaboraterRequest.setAdresse2(currentRow.getCell(columnMap.get("adresse2")).getStringCellValue());
                }

                if (columnMap.containsKey("adresse3")) {
                    collaboraterRequest.setAdresse3(currentRow.getCell(columnMap.get("adresse3")).getStringCellValue());
                }

                if (columnMap.containsKey("nb_enfants_saisi")) {
                    Cell cell = currentRow.getCell(columnMap.get("nb_enfants_saisi"));
                    boolean bool = cell != null && cell.getNumericCellValue() == 1;
                    collaboraterRequest.setNbEnfantsSaisi(bool);
                }

                if (columnMap.containsKey("nb_enfants")) {
                    collaboraterRequest.setNbEnfants((int) currentRow.getCell(columnMap.get("nb_enfants")).getNumericCellValue());
                }
                System.out.println("up12");

                if (columnMap.containsKey("nb_enfants_charge_saisi")) {
                    Cell cell = currentRow.getCell(columnMap.get("nb_enfants_charge_saisi"));
                    boolean bool = cell != null && cell.getNumericCellValue() == 1;
                    collaboraterRequest.setNbEnfantsChargeSaisi(bool);
                }
                System.out.println("up13");

                if (columnMap.containsKey("nb_enfants_charge")) {
                    collaboraterRequest.setNbEnfantCharge((int) currentRow.getCell(columnMap.get("nb_enfants_charge")).getNumericCellValue());
                }
                System.out.println("up14");

                if (columnMap.containsKey("nb_epouses_saisi")) {
                    Cell cell = currentRow.getCell(columnMap.get("nb_epouses_saisi"));
                    boolean bool = cell != null && cell.getNumericCellValue() == 1;
                    collaboraterRequest.setNbEpousesSaisi(bool);
                }
                System.out.println("up15");


                if (columnMap.containsKey("nb_epouses")) {
                    collaboraterRequest.setNbEpouses((int) currentRow.getCell(columnMap.get("nb_epouses")).getNumericCellValue());
                }

                if (columnMap.containsKey("nb_pers_charge")) {
                    collaboraterRequest.setNbPersCharge((int) currentRow.getCell(columnMap.get("nb_pers_charge")).getNumericCellValue());
                }
                System.out.println("up15");

                if (columnMap.containsKey("date_deces")) {
                    collaboraterRequest.setDateDeces(SharedService.handleDate(columnMap, currentRow, "date_deces", formatter));
                }
                System.out.println("up16");

                if (columnMap.containsKey("date_certif_deces")) {
                    collaboraterRequest.setDateCertifDeces(SharedService.handleDate(columnMap, currentRow, "date_certif_deces", formatter));
                }

                if (columnMap.containsKey("date_naturalisation")) {
                    collaboraterRequest.setDateNaturalisation(SharedService.handleDate(columnMap, currentRow, "date_naturalisation", formatter));
                }

                if (columnMap.containsKey("active")) {
                    Cell cell = currentRow.getCell(columnMap.get("active"));
                    boolean bool = cell != null && cell.getNumericCellValue() == 1;
                    collaboraterRequest.setActive(bool);
                }

                if (columnMap.containsKey("recrutable")) {
                    Cell cell = currentRow.getCell(columnMap.get("recrutable"));
                    boolean bool = cell != null && cell.getNumericCellValue() == 1;
                    collaboraterRequest.setRecrutable(bool);
                }

                if (columnMap.containsKey("exclu_declaration")) {
                    Cell cell = currentRow.getCell(columnMap.get("exclu_declaration"));
                    boolean bool = cell != null && cell.getNumericCellValue() == 1;
                    collaboraterRequest.setExcluDeclaration(bool);
                }

                if (columnMap.containsKey("matricule_recrutement")) {
                    collaboraterRequest.setMatriculeRecrutement(currentRow.getCell(columnMap.get("matricule_recrutement")).getStringCellValue());
                }

                if (columnMap.containsKey("nationality1")) {
                    Long coutrycode1 = countryRepository.findByNationality(currentRow.getCell(columnMap.get("nationality1")).getStringCellValue());
                    System.out.println("code country "+coutrycode1);
                    collaboraterRequest.setCountryCode1(coutrycode1);
                }

                if (columnMap.containsKey("nationality2")) {
                    Long coutrycode2 = countryRepository.findByNationality(currentRow.getCell(columnMap.get("nationality2")).getStringCellValue());
                    collaboraterRequest.setCountryCode2(coutrycode2);
                }

                collaboraterRequests.add(collaboraterRequest);
            }
            workbook.close();
            return collaboraterRequests;
        } catch (IOException e) {
            throw new RuntimeException("Erreur Au moment de lecture du fichier verifier les informations : " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveInBulk(CollaboraterRequestDto request) throws CountryException, CompanyException, CollaboraterException {
        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new CompanyException("Company avec Id Introuvable: [%s] :".formatted(request.getCompanyId())));
        Collaborater collaborater = Collaborater.builder()
                .matricule(request.getMatricule())
                .civilite(request.getCivilite())
                .initiales(request.getInitiales())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateNaissance(request.getDateNaissance())
                .lieuNaissance(request.getLieuNaissance())
                .sexe(request.getSexe())
                .civNomPrenom(request.getFirstName()+" "+request.getLastName())
                .civPrenomNom(request.getLastName()+" "+request.getFirstName())
                .cnie(request.getCnie())
                .cnieDelivreeLe(request.getCnieDelivreeLe())
                .cnieDelivreePar(request.getCnieDelivreePar())
                .cnieExpireLe(request.getCnieExpireLe())
                .numPermisSejour(request.getNumPermisSejour())
                .natPermisSejour(request.getNumPermisSejour())
                .permisSejourDelivreLe(request.getPermisSejourDelivreLe())
                .permisSejourDebVal(request.getPermisSejourDebVal())
                .permisSejourFinVal(request.getPermisSejourFinVal())
                .numPermisTravail(request.getNumPermisTravail())
                .natPermisTravail(request.getNatPermisTravail())
                .permisTravailDelivreLe(request.getPermisTravailDelivreLe())
                .permisTravailDebVal(request.getPermisTravailDebVal())
                .permisTravailFinVal(request.getPermisTravailFinVal())
                .numPassePort(request.getNumPassePort())
                .passePortDelivreLe(request.getPassePortDelivreLe())
                .passePortDelivrePar(request.getPassePortDelivrePar())
                .passePortExpireLe(request.getPassePortExpireLe())
                .telephone(request.getTelephone())
                .tel1(request.getTel1())
                .tel2(request.getTel2())
                .tel3(request.getTel3())
                .email1(request.getEmail1())
                .email2(request.getEmail2())
                .email3(request.getEmail3())
                .adresse1(request.getAdresse1())
                .adresse2(request.getAdresse2())
                .adresse3(request.getAdresse3())
                .nbEnfants(request.getNbEnfants())
                .nbEnfantsSaisi(request.getNbEnfantsSaisi())
                .nbEnfantCharge(request.getNbEnfantCharge())
                .nbEnfantsChargeSaisi(request.getNbEnfantsChargeSaisi())
                .nbPersCharge(request.getNbPersCharge())
                .nomJeuneFille(request.getNomJeuneFille())
                .nbEpouses(request.getNbEpouses())
                .nbEpousesSaisi(request.getNbEpousesSaisi())
                .dateNaturalisation(request.getDateNaturalisation())
                .active(request.getActive())
                .recrutable(request.getRecrutable())
                .excluDeclaration(request.getExcluDeclaration())
                .matriculeRecrutement(request.getMatriculeRecrutement())
                .dateCreation(LocalDateTime.now())
                .addedInBulk(true)
                .company(company)
                .countries(new ArrayList<>())
                .build();
        Collaborater saveCollaborater = collaboraterRepository.save(collaborater);
        System.out.println("test fin "+request.getCountryCode1());
        System.out.println("test fin "+request.getCountryCode2());
        addNationalitiesToCollaborater(saveCollaborater, request.getCountryCode1(), request.getCountryCode2());
    }

}
