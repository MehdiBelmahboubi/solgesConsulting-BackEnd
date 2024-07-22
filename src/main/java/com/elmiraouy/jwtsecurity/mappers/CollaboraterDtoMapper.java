package com.elmiraouy.jwtsecurity.mappers;

import com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto;
import com.elmiraouy.jwtsecurity.entities.Collaborater;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class CollaboraterDtoMapper implements Function<Collaborater, CollaboraterResponseDto> {
    @Override
    public CollaboraterResponseDto apply(Collaborater collaborater) {
        return new CollaboraterResponseDto(
                collaborater.getId(),
                collaborater.getMatricule(),
                collaborater.getCivilite(),
                collaborater.getFirstName(),
                collaborater.getLastName(),
                collaborater.getInitiales(),
                collaborater.getCivNomPrenom(),
                collaborater.getDateNaissance(),
                collaborater.getLieuNaissance(),
                collaborater.getSexe(),
                collaborater.getCnie(),
                collaborater.getCnieDelivreeLe(),
                collaborater.getCnieDelivreePar(),
                collaborater.getCnieExpireLe(),
                collaborater.getNumPermisSejour(),
                collaborater.getNatPermisSejour(),
                collaborater.getPermisSejourDelivreLe(),
                collaborater.getPermisSejourDebVal(),
                collaborater.getPermisSejourFinVal(),
                collaborater.getNumPermisTravail(),
                collaborater.getNatPermisTravail(),
                collaborater.getPermisTravailDelivreLe(),
                collaborater.getPermisTravailDebVal(),
                collaborater.getPermisTravailFinVal(),
                collaborater.getNumPassePort(),
                collaborater.getPassePortDelivreLe(),
                collaborater.getPassePortExpireLe(),
                collaborater.getPassePortDelivrePar(),
                collaborater.getTelephone(),
                collaborater.getTel1(),
                collaborater.getTel2(),
                collaborater.getTel3(),
                collaborater.getEmail1(),
                collaborater.getEmail2(),
                collaborater.getEmail3(),
                collaborater.getNbEnfantsSaisi(),
                collaborater.getNbEnfants(),
                collaborater.getNbEnfantsChargeSaisi(),
                collaborater.getNbEnfantCharge(),
                collaborater.getNomJeuneFille(),
                collaborater.getNbPersCharge(),
                collaborater.getNbEpousesSaisi(),
                collaborater.getNbEpouses(),
                collaborater.getDateNaturalisation(),
                collaborater.getActive(),
                collaborater.getRecrutable(),
                collaborater.getExcluDeclaration(),
                collaborater.getMatriculeRecrutement()
        );
    }
}
