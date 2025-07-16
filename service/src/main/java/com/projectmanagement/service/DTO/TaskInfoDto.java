package com.projectmanagement.service.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskInfoDto {

    private Long id;
    private String name;
    private Double totalRequiredManHour;
    private Double completedManHour;
    private LocalDateTime actualStartDate;     // ISO-8601 string format (örnek: 2025-07-16T14:30:00)
    private LocalDateTime actualEndDate;
    private LocalDateTime plannedStartDate;
    private LocalDateTime plannedEndDate;
    private Long projectId;
    private Long parentTaskId;
    private Integer priority;
    private String status;
    private Integer statusId;
    private Boolean hasChildTasks;
}
