package com.projectmanagement.service.interfaces;

import com.projectmanagement.domain.model.GenericApiResponse;
import com.projectmanagement.service.DTO.PieChartDataDto;

import java.util.List;

public interface IReportService {
    GenericApiResponse<String, List<PieChartDataDto>> getProjectStatus();
    GenericApiResponse<String,List<PieChartDataDto>> getProjectManHour();
    GenericApiResponse<String,List<PieChartDataDto>> getProjectTask();
}
