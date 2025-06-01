package com.projectmanagement.service.DTO;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UpdateProjectRequestDto {
    private long id ;
    private String name;
    private LocalDateTime plannedStartDate;
    private LocalDateTime plannedEndDate;
    private LocalDateTime actualStartDate;
    private LocalDateTime actualEndDate;
    private int categoryId;
    private int statusId;
}
