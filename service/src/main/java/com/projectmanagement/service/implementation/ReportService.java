package com.projectmanagement.service.implementation;

import com.projectmanagement.domain.enums.ProjectStatusEnum;
import com.projectmanagement.domain.enums.TaskStatusEnum;
import com.projectmanagement.domain.model.GenericApiResponse;
import com.projectmanagement.domain.model.PieChartDataModel;
import com.projectmanagement.repository.interfaces.ITaskRepository;
import com.projectmanagement.repository.interfaces.ProjectRepository;
import com.projectmanagement.service.DTO.PieChartDataDto;
import com.projectmanagement.service.interfaces.IReportService;
import com.projectmanagement.service.mapper.IProjectMapper;
import com.projectmanagement.service.mapper.IReportMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService implements IReportService {
    private ProjectRepository projectRepository;
    private IReportMapper reportMapper;
    private ITaskRepository taskRepository;

    public ReportService(ProjectRepository projectRepository, IReportMapper reportMapper, ITaskRepository taskRepository ){
        this.reportMapper = reportMapper;
        this.projectRepository  = projectRepository;
        this.taskRepository = taskRepository;
    }
    @Override
    public GenericApiResponse<String, List<PieChartDataDto>> getProjectStatus() {

        var genericApiResponse = new GenericApiResponse<String, List<PieChartDataDto>>();
        try {
            var pieChartDataModelList =  projectRepository.countProjectsByStatus();
var pieChartDataDtoList =reportMapper.toPieChartDataDtoList(pieChartDataModelList, ProjectStatusEnum.class);

            genericApiResponse.setSuccessData(pieChartDataDtoList);
        }
        catch (Exception e){
            genericApiResponse.setStatusCode(400);
            genericApiResponse.setErrorData("Unknown Error occured");

        }
        return genericApiResponse;
    }
    @Override
    public GenericApiResponse<String, List<PieChartDataDto>> getProjectManHour() {

        var genericApiResponse = new GenericApiResponse<String, List<PieChartDataDto>>();
        try {
            var pieChartDataModelCompletedList =  projectRepository.getCompletedManHour(null);
            var pieChartDataModelReamingList =  projectRepository.getRemainingManHour(null);
            List<PieChartDataModel> merged = new ArrayList<>(pieChartDataModelCompletedList);
            merged.addAll(pieChartDataModelReamingList);
            var pieChartDataDtoList =reportMapper.toPieChartDataDtoList(merged,null);

            genericApiResponse.setSuccessData(pieChartDataDtoList);
        }
        catch (Exception e){
            genericApiResponse.setStatusCode(400);
            genericApiResponse.setErrorData("Unknown Error occured");

        }
        return genericApiResponse;
    }

    @Override
    public GenericApiResponse<String, List<PieChartDataDto>> getProjectTask() {
        var genericApiResponse = new GenericApiResponse<String, List<PieChartDataDto>>();
        try {
            var pieChartDataModelist =  projectRepository.getProjectTaskStatus();
            var pieChartDataDtoList =reportMapper.toPieChartDataDtoList(pieChartDataModelist, TaskStatusEnum.class);

            genericApiResponse.setSuccessData(pieChartDataDtoList);
        }
        catch (Exception e){
            genericApiResponse.setStatusCode(400);
            genericApiResponse.setErrorData("Unknown Error occured");

        }
        return genericApiResponse;
    }
}
