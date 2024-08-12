
package com.elmiraouy.jwtsecurity.Dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CalendarRequestDto {
    private Long id;
    private String code;
    private String  name;
    private Boolean jourFerier;
    private Long companyId;
}
