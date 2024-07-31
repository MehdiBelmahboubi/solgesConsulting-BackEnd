package com.elmiraouy.jwtsecurity.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeUnitOrganisationalResponseDto {
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
    private String color;
    private Boolean addedInBulk;
    public TypeUnitOrganisationalResponseDto(Long id, String name, String code, Long companyId, int level, Boolean active,
                                             LocalDateTime createDate, LocalDateTime updateDate, LocalDateTime startDate, LocalDateTime endDate,String color) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.companyId = companyId;
        this.level=level;
        this.active=active;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.color=color;
    }
}
