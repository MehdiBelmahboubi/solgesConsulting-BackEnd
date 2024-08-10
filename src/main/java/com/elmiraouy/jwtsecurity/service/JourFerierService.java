package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.JourFerierRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.JourFerierResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.FeteException;
import com.elmiraouy.jwtsecurity.handlerException.JourFerierException;
import com.elmiraouy.jwtsecurity.handlerException.TypeFeteException;

import java.util.List;

public interface JourFerierService {
    public JourFerierResponseDto addJourFerier(JourFerierRequestDto jourFerierRequestDto) throws TypeFeteException, FeteException, JourFerierException, CompanyException;

    public List<JourFerierResponseDto> getAll(Long id);
}
