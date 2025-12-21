package com.projectmanagement.domain.model;

import lombok.Data;

@Data
public class LovModel {
    private long id;
    private String name;

    public LovModel(long id,String name){
        this.id = id;
        this.name = name;

    }
}
