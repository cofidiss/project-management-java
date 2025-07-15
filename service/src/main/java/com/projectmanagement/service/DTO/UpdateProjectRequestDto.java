package com.projectmanagement.service.DTO;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UpdateProjectRequestDto {
    private long id ;
    private String name;
    private String description;
    private LocalDateTime plannedStartDate;
    private LocalDateTime plannedEndDate;
    private int categoryId;
    private int statusId;
}
