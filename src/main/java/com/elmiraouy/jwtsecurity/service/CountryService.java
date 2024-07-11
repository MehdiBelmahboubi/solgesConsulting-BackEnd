package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.response.CountryResponseDto;
import com.elmiraouy.jwtsecurity.entities.Country;

import java.util.List;

public interface CountryService {
    public List<CountryResponseDto> getAllNationalities();
}
