package com.projectmanagement.domain.model;

import lombok.Data;

@Data
public class PieChartDataModel {

    private String label;
    private Number  value;
    public PieChartDataModel(String label, Number  value) {
        this.label = label;
        this.value = value;
    }
}
