package com.projectmanagement.service.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddProjectRequestDto {

    private String name;
    private LocalDateTime plannedStartDate;
    private LocalDateTime plannedEndDate;
    private int categoryId;
}
