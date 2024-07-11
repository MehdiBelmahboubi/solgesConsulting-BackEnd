package com.elmiraouy.jwtsecurity.mappers;

import com.elmiraouy.jwtsecurity.Dto.response.CountryResponseDto;
import com.elmiraouy.jwtsecurity.entities.Country;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CountryDtoMapper implements Function<Country, CountryResponseDto> {
    @Override
    public CountryResponseDto apply(Country country) {
        return new CountryResponseDto(
                country.getCode(),
                country.getNationality()
        );
    }
}
