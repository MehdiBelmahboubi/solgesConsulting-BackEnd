package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.CongesRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CongesResponseDto;
import com.elmiraouy.jwtsecurity.repository.CongesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CongesServiceImpl implements CongesService{
    private CongesRepository congesRepository;
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
    public CongesResponseDto createConges(CongesRequestDto congesRequestDto) {
        return null;
    }
}
