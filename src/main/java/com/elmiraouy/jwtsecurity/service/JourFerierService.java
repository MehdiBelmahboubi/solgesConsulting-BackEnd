package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.JourFerierRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.JourFerierResponseDto;

public interface JourFerierService {
    public JourFerierResponseDto addJourFerier(JourFerierRequestDto jourFerierRequestDto);
}
