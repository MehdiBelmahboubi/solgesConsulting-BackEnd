package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.FeteRequestDto;
import com.elmiraouy.jwtsecurity.Dto.request.JourFerierRequestDto;
import com.elmiraouy.jwtsecurity.Dto.request.TypeFeteRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.FeteResponseDto;
import com.elmiraouy.jwtsecurity.Dto.response.JourFerierResponseDto;
import com.elmiraouy.jwtsecurity.Dto.response.TypeFeteResponseDto;
import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.entities.Fete;
import com.elmiraouy.jwtsecurity.entities.JourFerier;
import com.elmiraouy.jwtsecurity.entities.TypeFete;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.FeteException;
import com.elmiraouy.jwtsecurity.handlerException.JourFerierException;
import com.elmiraouy.jwtsecurity.handlerException.TypeFeteException;
import com.elmiraouy.jwtsecurity.mappers.FeteDtoMapper;
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
    private final FeteDtoMapper feteDtoMapper;
    private final CompanyRepository companyRepository;
    @Override
    public JourFerierResponseDto addJourFerier(JourFerierRequestDto requestDto) throws FeteException, JourFerierException, CompanyException {
        if(requestDto.getDateFete().isBefore(LocalDateTime.now()))
        {
            throw new JourFerierException("Date Jour Ferie Invalide : [%s]".formatted(requestDto.getDateFete()));
        }
        Company company = companyRepository.findById(requestDto.getCompanyId())
                .orElseThrow(() -> new CompanyException("Company avec Id Introuvable: [%s] :".formatted(requestDto.getCompanyId())));
        Fete fete = feteRepository.findById(requestDto.getFeteId())
                .orElseThrow(()->new FeteException("Type Fete avec cette Id Introuvable: [%s]".formatted(requestDto.getFeteId())));
        JourFerier jourFerier = JourFerier.builder()
                .dateFete(requestDto.getDateFete())
                .nbrJour(requestDto.getNbrJour())
                .fete(fete)
                .active(true)
                .dateCreation(LocalDateTime.now())
                .company(company)
                .build();
        jourFerierRepository.save(jourFerier);
        return jourFerierDtoMapper.apply(jourFerier);
    }

    @Override
    public List<JourFerierResponseDto> getAll(Long id,Boolean statut) {
        List<JourFerierResponseDto> jourFerierResponseDtos = jourFerierRepository.findByCompanyAndActive(id,statut);
        return jourFerierResponseDtos;
    }

    @Override
    public List<FeteResponseDto> getFetes(Long id) {
        List<FeteResponseDto> feteResponseDtos = feteRepository.findByCompanyAndActive(id);
        return feteResponseDtos;
    }

    @Override
    public List<TypeFeteResponseDto> getTypesFetes(Long id) {
        List<TypeFeteResponseDto> typeFeteResponseDtos = typeFeteRepository.findByCompanyAndActive(id);
        return  typeFeteResponseDtos;
    }

    @Override
    public FeteResponseDto addFete(FeteRequestDto requestDto) throws CompanyException, TypeFeteException {
        Company company = companyRepository.findById(requestDto.getCompanyId())
                .orElseThrow(() -> new CompanyException("Company avec Id Introuvable: [%s] :".formatted(requestDto.getCompanyId())));
        TypeFete typeFete = typeFeteRepository.findById(requestDto.getTypeId())
                .orElseThrow(()->new TypeFeteException("Type Fete avec cette Id Introuvable: [%s]".formatted(requestDto.getTypeId())));
        Fete fete = Fete.builder()
                .code(requestDto.getCode())
                .libelle(requestDto.getLibelle())
                .typeFete(typeFete)
                .active(true)
                .dateCreation(LocalDateTime.now())
                .company(company)
                .build();
        feteRepository.save(fete);
        return feteDtoMapper.apply(fete);
    }

    @Override
    public TypeFeteResponseDto addTypeFete(TypeFeteRequestDto requestDto) throws CompanyException {
        Company company = companyRepository.findById(requestDto.getCompanyId())
                .orElseThrow(() -> new CompanyException("Company avec Id Introuvable: [%s] :".formatted(requestDto.getCompanyId())));
        TypeFete typeFete = TypeFete.builder()
                .libelle(requestDto.getLibelle())
                .reconduction(requestDto.getReconduction())
                .active(true)
                .dateCreation(LocalDateTime.now())
                .company(company)
                .build();
        typeFeteRepository.save(typeFete);
        return new TypeFeteResponseDto(typeFete.getId(), typeFete.getLibelle(), typeFete.getReconduction());
    }

    @Override
    public JourFerierResponseDto deleteCalendar(Long id) throws JourFerierException {
        JourFerier jourFerier = jourFerierRepository.findById(id)
                .orElseThrow(()->new JourFerierException("Jour Ferie avec Id Introuvable: [%s] :".formatted(id)));
        jourFerier.setActive(false);
        jourFerierRepository.save(jourFerier);
        return jourFerierDtoMapper.apply(jourFerier);
    }

    @Override
    public JourFerierResponseDto restoreCalendar(Long id) throws JourFerierException {
        JourFerier jourFerier = jourFerierRepository.findById(id)
                .orElseThrow(()->new JourFerierException("Jour Ferie avec Id Introuvable: [%s] :".formatted(id)));
        jourFerier.setActive(true);
        jourFerierRepository.save(jourFerier);
        return jourFerierDtoMapper.apply(jourFerier);
    }
}
