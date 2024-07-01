package com.elmiraouy.jwtsecurity.Dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
@AllArgsConstructor
public class AppUserResponseDto {
    private Long id;
    private String firstName;
    private  String lastName;
    private  String email;
    private  String accessToken;
    private   String refreshToken;
    private String address;
    private   String telephone;
    private   String ville;
    private   String urlImage;
    private    List<String> roles;
    private List<CompanyResponseDto> companies;

    public AppUserResponseDto(Long id, String firstName, String lastName, String email, String accessToken, String refreshToken, String address, String telephone, String ville, List<String> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.address = address;
        this.telephone = telephone;
        this.ville = ville;
        this.roles = roles;
    }


    public AppUserResponseDto(Long id, String firstName, String lastName, String email, String address, String telephone, String ville) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        this.ville = ville;
    }
}
