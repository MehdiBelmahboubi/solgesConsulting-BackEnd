package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.CalendarRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CalendarResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.CalendarException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;


import java.util.List;

public interface CalendarService {

    public List<CalendarResponseDto> getAll(Long id);

    public CalendarResponseDto addCalendar(CalendarRequestDto calendarRequestDto) throws CalendarException, CompanyException;
}