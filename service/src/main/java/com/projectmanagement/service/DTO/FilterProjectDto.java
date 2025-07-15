package com.projectmanagement.service.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FilterProjectDto {

    private Integer pageNumber;
    private Integer pageSize;
    private String name;
    private LocalDateTime actualStartDate;
    private LocalDateTime actualEndDate;
    private LocalDateTime plannedStartDate;
    private LocalDateTime plannedEndDate;
    private Integer statusId;
    private Integer categoryId;
}
