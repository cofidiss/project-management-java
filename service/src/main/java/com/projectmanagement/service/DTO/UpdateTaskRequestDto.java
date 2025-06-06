package com.projectmanagement.service.DTO;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UpdateTaskRequestDto {
    private Long id;
    private String name;
    private Long parentTaskId;
    private int categoryId;
    private int statusId;
    private int priority;
    private Double totalRequiredManHour;
    private Double completedManHour;
    private LocalDateTime plannedStartDate;
    private LocalDateTime plannedEndDate;
}
