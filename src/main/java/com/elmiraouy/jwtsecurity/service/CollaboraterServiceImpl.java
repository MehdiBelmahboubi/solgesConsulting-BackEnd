package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.CollaboraterRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.CollaboraterResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollaboraterServiceImpl implements CollaboraterService{
    @Override
    public List<CollaboraterResponseDto> findAll() {
        return null;
    }

    @Override
    public CollaboraterResponseDto addCollab(CollaboraterRequestDto CollaboraterRequestDto) {
        return null;
    }

    @Override
    public CollaboraterResponseDto deleteCollab(Long id) {
        return null;
    }

    @Override
    public CollaboraterResponseDto updateCollab(Long id, CollaboraterRequestDto CollaboraterRequestDto) {
        return null;
    }
}
