package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.Dto.request.AppUserRequestDto;
import com.elmiraouy.jwtsecurity.Dto.response.AppUserResponseDto;
import com.elmiraouy.jwtsecurity.entities.AppUser;
import com.elmiraouy.jwtsecurity.entities.Token;
import com.elmiraouy.jwtsecurity.handlerException.AppUserException;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.EntityNotFoundException;

import java.util.List;

public interface AppUserService {
    public List<AppUserResponseDto> findAll();
    public AppUserResponseDto findByEmail(String email) throws AppUserException;
    public AppUserResponseDto findById(Long id) throws AppUserException;
    public AppUserResponseDto delete(Long id);
    public AppUserResponseDto update(Long id ,AppUserRequestDto userRequestDto) throws AppUserException;
    public void addTokenToUser(AppUser appUser, Token token) throws AppUserException;
    public void addImageToUser(Long idImage,Long idUser) throws EntityNotFoundException, CompanyException, AppUserException;
    public AppUserResponseDto register(AppUserRequestDto appUserRequestDto) throws EntityNotFoundException, AppUserException, CompanyException;
    public  AppUserResponseDto confirmedEmail(AppUserRequestDto appUserRequestDto) throws AppUserException;
    public void addRoleToUser(Long idRole, Long idUser) throws EntityNotFoundException, CompanyException ;

}
