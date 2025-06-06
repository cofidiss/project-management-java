package com.projectmanagement.service.interfaces;

import com.projectmanagement.domain.model.GenericApiResponse;
import com.projectmanagement.service.DTO.AddProjectRequestDto;
import com.projectmanagement.service.DTO.UpdateProjectRequestDto;
import org.springframework.stereotype.Component;


public interface IProjectService {
    public GenericApiResponse<String,String> addProject(AddProjectRequestDto addProjectDto);

    public GenericApiResponse<String,String> updateProject(UpdateProjectRequestDto updateProjectRequestDto);

    public GenericApiResponse<String,String> deleteProject(long id);
}
