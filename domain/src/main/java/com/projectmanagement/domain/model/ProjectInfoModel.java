package com.projectmanagement.domain.model;

import java.time.LocalDateTime;

public class ProjectInfoModel {
    private Long id;

    private String name;


    private LocalDateTime actualStartDate;


    private LocalDateTime actualEndDate;

    private LocalDateTime   plannedStartDate;

    private LocalDateTime  plannedEndDate;


    private int categoryId;



    private int statusId;
}
