package com.projectmanagement.service.DTO;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
public class FilterProjectDto {

    private Integer pageNumber;
    private Integer pageSize;
    private String name;
    private OffsetDateTime actualStartDate;
    private OffsetDateTime  actualEndDate;
    private OffsetDateTime  plannedStartDate;
    private OffsetDateTime  plannedEndDate;
    private Integer statusId;
    private Integer categoryId;
}
