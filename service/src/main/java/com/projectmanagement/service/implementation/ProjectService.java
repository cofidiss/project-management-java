package com.projectmanagement.service.implementation;

import com.projectmanagement.domain.model.GenericApiResponse;
import com.projectmanagement.repository.interfaces.IProjectRepository;
import com.projectmanagement.service.DTO.AddProjectRequestDto;
import com.projectmanagement.service.DTO.UpdateProjectRequestDto;
import com.projectmanagement.service.interfaces.IProjectService;
import com.projectmanagement.service.mapper.IProjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ProjectService implements IProjectService {

    private IProjectRepository projectRepository;
    private  IProjectMapper projectMapper;
    public ProjectService(IProjectRepository projectRepository, IProjectMapper projectMapper){
this.projectMapper = projectMapper;
this.projectRepository  = projectRepository;
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
            genericApiResponse.setErrorData("entity did not found ");
            return  genericApiResponse;
        }
var entityToUpdate =  projectMapper.toProjectEntity(updateProjectRequestDto);
        projectRepository.save(entityToUpdate);

        return genericApiResponse;
    }
}
