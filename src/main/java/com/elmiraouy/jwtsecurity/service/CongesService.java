package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.CongesRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CongesResponseDto;
import org.springframework.data.domain.Page;

public interface CongesService {
    public Page<CongesResponseDto> findByCompany(Long companyId, int page, int size);

    public CongesResponseDto createConges(CongesRequestDto congesRequestDto);
}
