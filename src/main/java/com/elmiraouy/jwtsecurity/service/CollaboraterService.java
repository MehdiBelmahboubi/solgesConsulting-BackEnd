package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.CollaboraterRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto;

import java.util.List;

public interface CollaboraterService {
    public List<CollaboraterResponseDto> findAll();
    public CollaboraterResponseDto addCollab(CollaboraterRequestDto CollaboraterRequestDto);
    public CollaboraterResponseDto deleteCollab(Long id);
    public CollaboraterResponseDto updateCollab(Long id , CollaboraterRequestDto CollaboraterRequestDto);

}
