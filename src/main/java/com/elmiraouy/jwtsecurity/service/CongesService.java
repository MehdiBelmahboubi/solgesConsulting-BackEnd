package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.CongesRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CongesResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.CalendarException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import org.springframework.data.domain.Page;

public interface CongesService {
    public Page<CongesResponseDto> findByCompany(Long companyId, int page, int size);

    public CongesResponseDto createConges(CongesRequestDto congesRequestDto) throws CalendarException, CompanyException;
}
