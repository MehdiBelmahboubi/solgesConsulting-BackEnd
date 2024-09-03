package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.FeteRequestDto;
import com.elmiraouy.jwtsecurity.Dto.request.JourFerierRequestDto;
import com.elmiraouy.jwtsecurity.Dto.request.TypeFeteRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.FeteResponseDto;
import com.elmiraouy.jwtsecurity.Dto.response.JourFerierResponseDto;
import com.elmiraouy.jwtsecurity.Dto.response.TypeFeteResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.FeteException;
import com.elmiraouy.jwtsecurity.handlerException.JourFerierException;
import com.elmiraouy.jwtsecurity.handlerException.TypeFeteException;

import java.util.List;

public interface JourFerierService {
    public JourFerierResponseDto addJourFerier(JourFerierRequestDto jourFerierRequestDto) throws TypeFeteException, FeteException, JourFerierException, CompanyException;

    public List<JourFerierResponseDto> getAll(Long id,Boolean statut);

    public List<FeteResponseDto> getFetes(Long id);

    public List<TypeFeteResponseDto> getTypesFetes(Long id);

    public FeteResponseDto addFete(FeteRequestDto feteRequestDto) throws CompanyException, TypeFeteException;

    public TypeFeteResponseDto addTypeFete(TypeFeteRequestDto typeFeteRequestDto) throws CompanyException;

    public JourFerierResponseDto deleteCalendar(Long id) throws JourFerierException;

    public JourFerierResponseDto restoreCalendar(Long id) throws JourFerierException;
}
