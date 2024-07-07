package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.CollaboraterRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto;
import com.elmiraouy.jwtsecurity.handlerException.CollaboraterException;

import java.util.List;

public interface CollaboraterService {
    public List<CollaboraterResponseDto> findAll();
    public CollaboraterResponseDto createCollab(CollaboraterRequestDto CollaboraterRequestDto) throws CollaboraterException;
    public CollaboraterResponseDto deleteCollab(Long id);
    public CollaboraterResponseDto updateCollab(Long id , CollaboraterRequestDto CollaboraterRequestDto);

}
