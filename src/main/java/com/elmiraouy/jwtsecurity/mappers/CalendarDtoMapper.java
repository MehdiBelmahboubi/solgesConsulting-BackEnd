package com.elmiraouy.jwtsecurity.mappers;

import com.elmiraouy.jwtsecurity.Dto.response.CalendarResponseDto;
import com.elmiraouy.jwtsecurity.entities.Calendar;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CalendarDtoMapper implements Function<Calendar, CalendarResponseDto> {
    @Override
    public CalendarResponseDto apply(Calendar calendar) {
        return new CalendarResponseDto(
                calendar.getId(),
                calendar.getCode(),
                calendar.getName(),
                calendar.getJourFerier()
        );
    }
}
