package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.CalendarRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CalendarResponseDto;
import com.elmiraouy.jwtsecurity.entities.Calendar;
import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.handlerException.CalendarException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.mappers.CalendarDtoMapper;
import com.elmiraouy.jwtsecurity.repository.CalendarRepository;
import com.elmiraouy.jwtsecurity.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {
    private final CalendarRepository calendarRepository;
    private final CompanyRepository companyRepository;
    private final CalendarDtoMapper calendarDtoMapper; // Make sure this is defined or injected properly

    @Override
    public List<CalendarResponseDto> getAll(Long id,Boolean statut) {
        List<CalendarResponseDto> calendarResponseDtos = calendarRepository.findByCompanyAndActive(id,statut);
        return calendarResponseDtos;
    }

    @Override
    public CalendarResponseDto addCalendar(CalendarRequestDto calendarRequestDto) throws CalendarException, CompanyException {
        Company company = companyRepository.findById(calendarRequestDto.getCompanyId())
                .orElseThrow(() -> new CompanyException("Company with ID not found: [%s]".formatted(calendarRequestDto.getCompanyId())));

        Calendar existingCalendar = calendarRepository.findByCodeAndCompany(calendarRequestDto.getId(), calendarRequestDto.getCompanyId());
        if (existingCalendar != null) {
            throw new CalendarException("Calendar with code [%s] already exists for this company.".formatted(calendarRequestDto.getCode()));
        }

        Calendar calendar = Calendar.builder()
                .code(calendarRequestDto.getCode())
                .libelle(calendarRequestDto.getLibelle())
                .jourFerier(calendarRequestDto.getJourFerier())
                .daysOfWeek(calendarRequestDto.getDaysOfWeek())
                .active(true)
                .dateCreation(LocalDateTime.now())
                .company(company)
                .build();

        calendarRepository.save(calendar);

        return calendarDtoMapper.apply(calendar);
    }

    @Override
    public CalendarResponseDto getById(Long id) throws CalendarException {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new CalendarException("Calendar with ID [%s] not found.".formatted(id)));

        return calendarDtoMapper.apply(calendar);
    }

    @Override
    public CalendarResponseDto deleteCalendar(Long id) throws CalendarException {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new CalendarException("Calendar with ID [%s] not found.".formatted(id)));
        calendar.setActive(false);
        calendarRepository.save(calendar);
        return calendarDtoMapper.apply(calendar);
    }

    @Override
    public CalendarResponseDto restoreCalendar(Long id) throws CalendarException {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new CalendarException("Calendar with ID [%s] not found.".formatted(id)));
        calendar.setActive(true);
        calendarRepository.save(calendar);
        return calendarDtoMapper.apply(calendar);
    }
}
