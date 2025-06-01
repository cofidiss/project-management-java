package com.projectmanagement.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "task")
public class TaskEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "total_required_man_hour")
    private Double totalRequiredManHour;

    @Column(name = "completed_man_hour")
    private Double completedManHour;

    @Column(name = "actual_start_date")
    private LocalDateTime actualStartDate;

    @Column(name = "actual_end_date")
    private LocalDateTime actualEndDate;
    @Column(name = "planned_start_date")
    private LocalDateTime plannedStartDate;

    @Column(name = "planned_end_date")
    private LocalDateTime plannedEndDate;
    // Relations

    @JoinColumn(name = "project_id")
    private Long projectId;


    @JoinColumn(name = "parent_task_id")
    private Long parentTaskId;


    @JoinColumn(name = "category_id")
    private Long categoryId;


    @JoinColumn(name = "status_id")
    private Long statusId;
}
