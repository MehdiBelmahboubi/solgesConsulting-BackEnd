package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.response.CountryResponseDto;
import com.elmiraouy.jwtsecurity.entities.Country;
import com.elmiraouy.jwtsecurity.mappers.CountryDtoMapper;
import com.elmiraouy.jwtsecurity.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService{
    private final CountryRepository countryRepository;
    private final CountryDtoMapper countryDtoMapper;
    @Override
    public List<CountryResponseDto> getAllNationalities() {
        List<Country> countries = countryRepository.findAll();
        return countries.stream().map(countryDtoMapper).toList();
    }
}
