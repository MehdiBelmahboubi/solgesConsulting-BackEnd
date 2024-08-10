package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.JourFerierRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.JourFerierResponseDto;
import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.entities.Fete;
import com.elmiraouy.jwtsecurity.entities.JourFerier;
import com.elmiraouy.jwtsecurity.entities.TypeFete;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.FeteException;
import com.elmiraouy.jwtsecurity.handlerException.JourFerierException;
import com.elmiraouy.jwtsecurity.handlerException.TypeFeteException;
import com.elmiraouy.jwtsecurity.mappers.JourFerierDtoMapper;
import com.elmiraouy.jwtsecurity.repository.CompanyRepository;
import com.elmiraouy.jwtsecurity.repository.FeteRepository;
import com.elmiraouy.jwtsecurity.repository.JourFerierRepository;
import com.elmiraouy.jwtsecurity.repository.TypeFeteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JourFerierServiceImpl implements JourFerierService{
    private final TypeFeteRepository typeFeteRepository;
    private final FeteRepository feteRepository;
    private final JourFerierRepository jourFerierRepository;
    private final JourFerierDtoMapper jourFerierDtoMapper;
    private final CompanyRepository companyRepository;
    @Override
    public JourFerierResponseDto addJourFerier(JourFerierRequestDto jourFerierRequestDto) throws TypeFeteException, FeteException, JourFerierException, CompanyException {
        TypeFete typeFete;
        Fete fete;
        if(jourFerierRequestDto.getDateFete().isBefore(LocalDateTime.now()))
        {
            throw new JourFerierException("Date Jour Ferie Invalide : [%s]".formatted(jourFerierRequestDto.getDateFete()));
        }
        Company company = companyRepository.findById(jourFerierRequestDto.getCompanyId())
                .orElseThrow(() -> new CompanyException("Company avec Id Introuvable: [%s] :".formatted(jourFerierRequestDto.getCompanyId())));
        if(jourFerierRequestDto.getFete()!=null){
            if(jourFerierRequestDto.getFete().getTypeFete()!=null){
                 typeFete = TypeFete.builder()
                        .libelle(jourFerierRequestDto.getFete().getTypeFete().getLibelle())
                        .recondiction(jourFerierRequestDto.getFete().getTypeFete().getRecondiction())
                        .active(true)
                        .dateCreation(LocalDateTime.now())
                         .company(company)
                         .build();
                 typeFeteRepository.save(typeFete);
            }else {
                typeFete = typeFeteRepository.findById(jourFerierRequestDto.getFete().getTypeId())
                        .orElseThrow(()->new TypeFeteException("Type Fete avec cette Id Introuvable: [%s]".formatted(jourFerierRequestDto.getFete().getTypeId())));
            }
            fete = Fete.builder()
                    .code(jourFerierRequestDto.getFete().getCode())
                    .libelle(jourFerierRequestDto.getFete().getLibelle())
                    .typeFete(typeFete)
                    .active(true)
                    .dateCreation(LocalDateTime.now())
                    .company(company)
                    .build();
            feteRepository.save(fete);
        }else {
            fete =feteRepository.findById(jourFerierRequestDto.getFeteId())
                    .orElseThrow(()->new FeteException("Fete avec cette Id Introuvable: [%s]".formatted(jourFerierRequestDto.getFeteId())));
        }
        JourFerier jourFerier = JourFerier.builder()
                .dateFete(jourFerierRequestDto.getDateFete())
                .nbrJour(jourFerierRequestDto.getNbrJour())
                .fete(fete)
                .active(true)
                .dateCreation(LocalDateTime.now())
                .company(company)
                .build();
        jourFerierRepository.save(jourFerier);
        return jourFerierDtoMapper.apply(jourFerier);
    }

    @Override
    public List<JourFerierResponseDto> getAll(Long id) {
        List<JourFerierResponseDto> jourFerierResponseDtos = jourFerierRepository.findByCompanyAndActive(id);
        return jourFerierResponseDtos;
    }
}
