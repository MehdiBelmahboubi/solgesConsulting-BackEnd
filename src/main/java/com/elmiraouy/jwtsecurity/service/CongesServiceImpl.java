package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.CongesRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CongesResponseDto;
import com.elmiraouy.jwtsecurity.entities.Calendar;
import com.elmiraouy.jwtsecurity.entities.Conges;
import com.elmiraouy.jwtsecurity.entities.Droit;
import com.elmiraouy.jwtsecurity.handlerException.CalendarException;
import com.elmiraouy.jwtsecurity.mappers.CongesDtoMapper;
import com.elmiraouy.jwtsecurity.repository.CalendarRepository;
import com.elmiraouy.jwtsecurity.repository.CongesRepository;
import com.elmiraouy.jwtsecurity.repository.DroitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CongesServiceImpl implements CongesService{
    private final CongesRepository congesRepository;
    private final DroitRepository droitRepository;
    private final CongesDtoMapper congesDtoMapper;
    private final CalendarRepository calendarRepository;
    @Override
    public Page<CongesResponseDto> findByCompany(Long companyId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<CongesResponseDto> congesPage = congesRepository.findAllByCompanyAndActive(companyId, pageable);

        if (congesPage.isEmpty()) {
            return Page.empty();
        }
        return congesPage;
    }

    @Override
    public CongesResponseDto createConges(CongesRequestDto congesRequestDto) throws CalendarException {
        Calendar calendar = calendarRepository.findById(congesRequestDto.getCalendarId())
                .orElseThrow(()->new CalendarException("Calendar with this Id Introuvable: [%s] :".formatted(congesRequestDto.getCalendarId())));
        List<Droit> droits = congesRequestDto.getDroits().stream()
                .map(droitRequestDto -> {
                    Droit droit = Droit.builder()
                            .nbrJour(droitRequestDto.getNbrJour())
                            .sexes(droitRequestDto.getSexes())
                            .contractTypes(droitRequestDto.getContractTypes())
                            .classificationTypes(droitRequestDto.getClassificationTypes())
                            .droitType(droitRequestDto.getDroitType())
                            .build();
                    return droitRepository.save(droit);
                })
                .collect(Collectors.toList());

        Conges conges = Conges.builder()
                .code(congesRequestDto.getCode())
                .imputablePaix(congesRequestDto.getImputablePaix())
                .statut(congesRequestDto.getStatut())
                .dateValidite(congesRequestDto.getDateValidite())
                .dateFinValidite(congesRequestDto.getDateFinValidite())
                .unite(congesRequestDto.getUnite())
                .autoriserDefalcation(congesRequestDto.getNbrDefalcation() != null)
                .nbrDefalcation(congesRequestDto.getNbrDefalcation())
                .autoriserRecondiction(congesRequestDto.getDelaiRecondiction() != null)
                .delaiRecondiction(congesRequestDto.getDelaiRecondiction())
                .minJour(congesRequestDto.getMinJour())
                .maxJour(congesRequestDto.getMaxJour())
                .reliquatReconduire(congesRequestDto.getNbrAnneeReliquat() != null)
                .nbrAnneeReliquat(congesRequestDto.getNbrAnneeReliquat())
                .droits(droits)
                .calendar(calendar)
                .build();

        Conges savedConges = congesRepository.save(conges);
        return congesDtoMapper.apply(savedConges);
    }
}
