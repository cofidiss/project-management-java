package com.projectmanagement.service.implementation;

import com.projectmanagement.domain.entity.TaskEntity;
import com.projectmanagement.domain.enums.ProjectCategoryEnum;
import com.projectmanagement.domain.enums.ProjectStatusEnum;
import com.projectmanagement.domain.model.GenericApiResponse;

import com.projectmanagement.repository.interfaces.ITaskRepository;
import com.projectmanagement.repository.interfaces.ProjectRepository;
import com.projectmanagement.repository.interfaces.ProjectRepositoryCustom;
import com.projectmanagement.service.DTO.*;
import com.projectmanagement.service.interfaces.IProjectService;
import com.projectmanagement.service.mapper.IProjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService implements IProjectService {
    private ITaskRepository taskRepository;
    private ProjectRepository projectRepository;
    private  IProjectMapper projectMapper;
    public ProjectService(ProjectRepository projectRepository, IProjectMapper projectMapper, ITaskRepository taskRepository ){
this.projectMapper = projectMapper;
this.projectRepository  = projectRepository;
this.taskRepository = taskRepository;
    }
    @Override
    public GenericApiResponse<String, String> addProject(AddProjectRequestDto addProjectDto) {
        var genericApiResponse = new GenericApiResponse<String, String>();
        try {
            var entityToAdd =     projectMapper.toProjectEntity(addProjectDto);

            projectRepository.save(entityToAdd);
            genericApiResponse.setSuccessData(entityToAdd.getId().toString());
        }
catch (Exception e){
    genericApiResponse.setStatusCode(400);
    genericApiResponse.setErrorData("Unknown Error occured");

}
        return genericApiResponse;
    }

    @Override
    public GenericApiResponse<String, String> updateProject(UpdateProjectRequestDto updateProjectRequestDto) {
var genericApiResponse = new GenericApiResponse<String, String>();
        var optEntityInDb = projectRepository.findById(updateProjectRequestDto.getId());
        if(optEntityInDb.isEmpty()){
             genericApiResponse.setStatusCode(404);
            genericApiResponse.setErrorData("project did not found ");
            return  genericApiResponse;
        }
var entityToUpdate =  projectMapper.toProjectEntity(updateProjectRequestDto,optEntityInDb.get());
        projectRepository.save(entityToUpdate);

        return genericApiResponse;
    }
@Transactional
    @Override
    public GenericApiResponse<String, String> deleteProject(long id) {

        var genericApiResponse = new GenericApiResponse<String, String>();
        var optEntityInDb = projectRepository.findById(id);
        if(optEntityInDb.isEmpty()){
            genericApiResponse.setStatusCode(404);
            genericApiResponse.setErrorData("project did not found ");
            return  genericApiResponse;
        }
        projectRepository.deleteById(id);
       var taskToDelete =  taskRepository.findByProjectId(id);
        taskRepository.deleteAllById( taskToDelete.stream()
                .map(TaskEntity::getId)
                .collect(Collectors.toList()));


        return genericApiResponse;

    }

    @Override
    public GenericApiResponse< String,ProjectInfoDto> getProject(long projectId) {

        var genericApiResponse = new GenericApiResponse<String,ProjectInfoDto >();
        var optEntityInDb = projectRepository.findById(projectId);
        if(optEntityInDb.isEmpty()){
            genericApiResponse.setStatusCode(404);
            genericApiResponse.setErrorData("project did not found ");
            return  genericApiResponse;
        }

       var projectInfoDto =  projectMapper.toProjectInfoDto(optEntityInDb.get());
        genericApiResponse.setSuccessData(projectInfoDto);

        return genericApiResponse;

    }

@Override
public <TLov extends Enum<TLov>> GenericApiResponse<String, List<LovDto>>  getLovDto(Class<TLov> enumClass) {
        GenericApiResponse<String, List<LovDto>> genericApiResponse = new GenericApiResponse<>();
        List<LovDto> lovDtoList = new ArrayList<>();

        for (TLov enumConstant : enumClass.getEnumConstants()) {
            LovDto lovDto = new LovDto();
            lovDto.setId(enumConstant.ordinal());
            lovDto.setName(enumConstant.name());
            lovDtoList.add(lovDto);
        }

        genericApiResponse.setSuccessData(lovDtoList);
        return genericApiResponse;
    }

    @Override
    public GenericApiResponse<String, List<ProjectInfoDto>> FilterProject(FilterProjectDto filterProjectDto) {

        GenericApiResponse<String, List<ProjectInfoDto>> genericApiResponse = new GenericApiResponse<>();
        var filterProjectModel = projectMapper.toFilterProjectModel(filterProjectDto);
 var projectEntities = projectRepository.filterrProjects(filterProjectModel);
var projectInfoDtoList=   projectMapper.toProjectInfoDtoList(projectEntities);
        genericApiResponse.setSuccessData(projectInfoDtoList);
        return genericApiResponse;
    }

}
