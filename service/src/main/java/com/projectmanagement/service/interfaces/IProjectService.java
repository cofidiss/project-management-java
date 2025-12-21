package com.projectmanagement.service.interfaces;

import com.projectmanagement.domain.entity.ProjectEntity;
import com.projectmanagement.domain.model.GenericApiResponse;
import com.projectmanagement.service.DTO.*;
import org.springframework.stereotype.Component;

import java.util.List;


public interface IProjectService {
    public GenericApiResponse<String,String> addProject(AddProjectRequestDto addProjectDto);

    public GenericApiResponse<String,String> updateProject(UpdateProjectRequestDto updateProjectRequestDto);

    public GenericApiResponse<String,String> deleteProject(long id);

    public GenericApiResponse<String,ProjectInfoDto>  getProject(long projectId);

    public <TLov extends Enum<TLov>> GenericApiResponse<String, List<LovDto>> getLovDto(Class<TLov> enumClass) ;


    public GenericApiResponse<String,List<ProjectInfoDto>> FilterProject(FilterProjectDto filterProjectDto);

    public GenericApiResponse<String,List<ProjectDetailDto>> getProjectDetail(long projectId);


    public GenericApiResponse<String,List<LovDto>> getProjectLov();
}
