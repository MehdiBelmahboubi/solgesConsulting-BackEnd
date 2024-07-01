package com.elmiraouy.jwtsecurity.Dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class TypeUnitOrganisationalRequest {
    private Long id ;
    private String name;
    private String code;
    private Long companyId;
    private Integer level;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean active;
    private Long idUserCreated;
    private String color;
}
