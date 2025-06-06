package com.projectmanagement.service.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AddTaskRequestDto {

    private String name;
    private Long projectId;
    private Long parentTaskId;
    private int categoryId;
    private int priority;
    private Double totalRequiredManHour;
    private LocalDateTime plannedStartDate;
    private LocalDateTime plannedEndDate;


}
