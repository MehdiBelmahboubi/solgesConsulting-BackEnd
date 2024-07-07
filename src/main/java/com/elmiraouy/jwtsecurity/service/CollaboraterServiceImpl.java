package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.CollaboraterRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto;
import com.elmiraouy.jwtsecurity.entities.Collaborater;
import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.enums.Civilite;
import com.elmiraouy.jwtsecurity.enums.Sexe;
import com.elmiraouy.jwtsecurity.handlerException.CollaboraterException;
import com.elmiraouy.jwtsecurity.mappers.CollaboraterDtoMapper;
import com.elmiraouy.jwtsecurity.repository.CollaboraterRepository;
import com.elmiraouy.jwtsecurity.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CollaboraterServiceImpl implements CollaboraterService{
    private final CollaboraterRepository collaboraterRepository;
    private final CompanyRepository companyRepository;
    private final CollaboraterDtoMapper collaboraterDtoMapper;
    @Override
    public List<CollaboraterResponseDto> findAll() {
        List<Collaborater> collaboraters=collaboraterRepository.findAll();
        return collaboraters.stream().map(collaboraterDtoMapper).toList();
    }

    @Override
    public CollaboraterResponseDto createCollab(CollaboraterRequestDto request) throws CollaboraterException {
        Optional<Collaborater> oCollaborater = collaboraterRepository.findByMatriculeAndCompany(request.getMatricule(),request.getCompany());
        if(oCollaborater.isPresent())
            throw new CollaboraterException("un collaborateur avec ce Matricule deja creer dans cette company: [%s] :".formatted(request.getMatricule()));
        Sexe sexe;
        if(request.getCivilite()==Civilite.Mr){
            sexe=Sexe.Homme;
        }else {
            sexe=Sexe.Femme;
        }
        boolean nbEnfantsSaisi = request.getNbEnfants() != null;
        boolean nbEnfantsChargeSaisi = request.getNbEnfantCharge() != null;
        boolean nbEpousesSaisi = request.getNbEpouses() != null;
        var collaborater = Collaborater.builder()
                .matricule(request.getMatricule())
                .civilite(request.getCivilite())
                .initiales(request.getInitiales())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .dateNaissance(request.getDateNaissance())
                .lieuNaissance(request.getLieuNaissance())
                .sexe(sexe)
                .civNomPrenom(request.getFirstname()+" "+request.getLastname())
                .civPrenomNom(request.getLastname()+" "+request.getFirstname())
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
                .nbEnfants(request.getNbEnfants())
                .nbEnfantsSaisi(nbEnfantsSaisi)
                .nbEnfantCharge(request.getNbEnfantCharge())
                .nbEnfantsChargeSaisi(nbEnfantsChargeSaisi)
                .nbPersCharge(request.getNbPersCharge())
                .nomJeuneFille(request.getNomJeuneFille())
                .nbEpouses(request.getNbEpouses())
                .nbEpousesSaisi(nbEpousesSaisi)
                .dateNaturalisation(request.getDateNaturalisation())
                .active(request.getActive())
                .recrutable(request.getRecrutable())
                .excluDeclaration(request.getExcluDeclaration())
                .matriculeRecrutement(request.getMatriculeRecrutement())
                .observation(request.getObservation())
                .dateCreation(LocalDateTime.now())
                .creePar(request.getCreePar())
                .build();
        Collaborater saveCollaborater = collaboraterRepository.save(collaborater);
        return collaboraterDtoMapper.apply(saveCollaborater);
    }

    @Override
    public CollaboraterResponseDto deleteCollab(Long id) {
        return null;
    }

    @Override
    public CollaboraterResponseDto updateCollab(Long id, CollaboraterRequestDto CollaboraterRequestDto) {
        return null;
    }
}
