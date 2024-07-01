package com.elmiraouy.jwtsecurity.Dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponseDto {

    protected Long id;
    private String token;
    private boolean expired;
    private boolean revoked;

}
