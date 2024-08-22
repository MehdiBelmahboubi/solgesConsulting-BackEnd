
package com.elmiraouy.jwtsecurity.Dto.request;

import com.elmiraouy.jwtsecurity.enums.DayOfWeek;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class CalendarRequestDto {
    private Long id;
    private String code;
    private String  libelle;
    private Boolean jourFerier;
    private Long companyId;
    private Set<DayOfWeek> daysOfWeek;
}
