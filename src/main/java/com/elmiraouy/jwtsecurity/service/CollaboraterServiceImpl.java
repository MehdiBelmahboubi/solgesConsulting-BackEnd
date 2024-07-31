package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.CollaboraterRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.*;
import com.elmiraouy.jwtsecurity.entities.*;
import com.elmiraouy.jwtsecurity.enums.Civilite;
import com.elmiraouy.jwtsecurity.enums.Sexe;
import com.elmiraouy.jwtsecurity.handlerException.*;
import com.elmiraouy.jwtsecurity.mappers.CollaboraterDtoMapper;
import com.elmiraouy.jwtsecurity.mappers.ContractDtoMapper;
import com.elmiraouy.jwtsecurity.mappers.ContractTypeDtoMapper;
import com.elmiraouy.jwtsecurity.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollaboraterServiceImpl implements CollaboraterService{
    private final CollaboraterRepository collaboraterRepository;
    private final CompanyRepository companyRepository;
    private final CollaboraterDtoMapper collaboraterDtoMapper;
    private final CountryRepository countryRepository;
    private final ContractRepository contractRepository;
    private final ContractTypeRepository contractTypeRepository;
    private final ClassificationRepository classificationRepository;
    private final ContractTypeDtoMapper contractTypeDtoMapper;
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
        Company company = companyRepository.findById(request.getCompany_id())
                .orElseThrow(() -> new CompanyException("Company avec Id Introuvable: [%s] :".formatted(request.getCompany_id())));
//        if(collaboraterRepository.findByCnie(request.getCnie()).isPresent()){
//            throw new CollaboraterException("un collaborateur avec ce CNIE est deja creer: [%s] :".formatted(request.getCnie()));
//        }else
          if(collaboraterRepository.findByMatriculeAndCompany(request.getMatricule(), company).isPresent()) {
            throw new CollaboraterException("un collaborateur avec ce Matricule deja creer dans cette company: [%s] :".formatted(request.getMatricule()));
        }
        Collaborater collaborater = buildCollaborater(request, company);
        Collaborater saveCollaborater = collaboraterRepository.save(collaborater);
        addNationalitiesToCollaborater(saveCollaborater, request.getCountryCode1(), request.getCountryCode2());
        return collaboraterDtoMapper.apply(saveCollaborater);
    }

    public Collaborater buildCollaborater(CollaboraterRequestDto request, Company company) throws CollaboraterException {
        Civilite civilite = request.getSexe() == Sexe.Homme ? Civilite.Mr : Civilite.Mme;
        Date dateNaissance = request.getDateNaissance();
        if (dateNaissance.after(new Date())) {
            throw new CollaboraterException("Date Naissance Error : " + dateNaissance);
        }
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
        Date dateNaissance = request.getDateNaissance();
        if (dateNaissance.after(new Date())) {
            throw new CollaboraterException("Date Naissance Error : " + dateNaissance);
        }
//        if(collaboraterRepository.findByCnie(request.getCnie()).isPresent()){
//            throw new CollaboraterException("un collaborateur avec ce CNIE est deja creer: [%s] :".formatted(request.getCnie()));
//        }else if(collaboraterRepository.findByMatriculeAndCompanyId(request.getMatricule(), request.getCompany_id()).isPresent()) {
//            throw new CollaboraterException("un collaborateur avec ce Matricule deja creer dans cette company: [%s] :".formatted(request.getMatricule()));
//        }
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
        collaborater.setNbEnfants(request.getNbEnfants());
        collaborater.setNbEnfantsSaisi(request.getNbEnfants() != null);
        collaborater.setNbEnfantCharge(request.getNbEnfantCharge());
        collaborater.setNbEnfantsChargeSaisi(request.getNbEnfantCharge() != null);
        collaborater.setNbPersCharge(request.getNbPersCharge());
        collaborater.setNomJeuneFille(request.getNomJeuneFille());
        collaborater.setNbEpouses(request.getNbEpouses());
        collaborater.setNbEpousesSaisi(request.getNbEpouses() != null);
        collaborater.setDateNaturalisation(request.getDateNaturalisation());
        collaborater.setActive(request.getActive());
        collaborater.setRecrutable(request.getRecrutable());
        collaborater.setExcluDeclaration(request.getExcluDeclaration());
        collaborater.setMatriculeRecrutement(request.getMatriculeRecrutement());

        collaboraterRepository.save(collaborater);

        return collaboraterDtoMapper.apply(collaborater);
    }
}
