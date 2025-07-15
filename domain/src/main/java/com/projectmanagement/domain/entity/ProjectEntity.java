package com.projectmanagement.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.Date;

@Data
@Entity
@Table(name = "project")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "actual_start_date" ,columnDefinition = "TIMESTAMP")
    private LocalDateTime  actualStartDate;

    @Column(name = "actual_end_date",columnDefinition = "TIMESTAMP")
    private LocalDateTime actualEndDate;
    @Column(name = "planned_start_date",columnDefinition = "TIMESTAMP")
    private LocalDateTime   plannedStartDate;
    @Column(name = "planned_end_date",columnDefinition = "TIMESTAMP")
    private LocalDateTime  plannedEndDate;

    @JoinColumn(name = "category_id")
    private int categoryId;
    @JoinColumn(name = "description")
    private String description;

    @JoinColumn(name = "status_id")
    private int statusId;

}
