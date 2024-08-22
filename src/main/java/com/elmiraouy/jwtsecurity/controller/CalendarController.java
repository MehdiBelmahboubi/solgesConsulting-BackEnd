package com.elmiraouy.jwtsecurity.controller;

import com.elmiraouy.jwtsecurity.Dto.request.CalendarRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CalendarResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.CalendarException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/client/calendar")

public class CalendarController {
    private final CalendarService calendarService;

    @GetMapping
    public ResponseEntity<List<CalendarResponseDto>> getAll(@RequestParam Long id,@RequestParam Boolean statut){
        return ResponseEntity.ok(calendarService.getAll(id,statut));
    }

    @PostMapping
    public ResponseEntity<CalendarResponseDto> addCalendar(@RequestBody CalendarRequestDto CalendarRequestDto) throws CalendarException, CompanyException {
        return ResponseEntity.ok(calendarService.addCalendar(CalendarRequestDto));
    }

}
