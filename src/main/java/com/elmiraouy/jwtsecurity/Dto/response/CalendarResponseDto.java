package com.elmiraouy.jwtsecurity.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Builder
@Data
@AllArgsConstructor
public class CalendarResponseDto {
    private Long id;
    private String code;
    private String  name;
    private Boolean jourFerier;

    public CalendarResponseDto (Long id, String code, String  name, boolean jourFerier){
        this.id=id;
        this.code=code;
        this.name=name;
        this.jourFerier=jourFerier;

    }
}
