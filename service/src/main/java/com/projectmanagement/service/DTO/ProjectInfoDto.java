package com.projectmanagement.service.DTO;



import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ProjectInfoDto {


    private Long id;

    private String name;


    private LocalDateTime actualStartDate;


    private LocalDateTime actualEndDate;

    private LocalDateTime   plannedStartDate;

    private LocalDateTime  plannedEndDate;


    private int categoryId;
    private String category;
    private String status;
    private String description;
    private int statusId;
}
