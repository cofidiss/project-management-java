package com.projectmanagement.service.implementation;

import com.projectmanagement.domain.entity.TaskEntity;
import com.projectmanagement.domain.model.GenericApiResponse;
import com.projectmanagement.repository.interfaces.IProjectRepository;
import com.projectmanagement.repository.interfaces.ITaskRepository;
import com.projectmanagement.service.DTO.AddProjectRequestDto;
import com.projectmanagement.service.DTO.UpdateProjectRequestDto;
import com.projectmanagement.service.interfaces.IProjectService;
import com.projectmanagement.service.mapper.IProjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ProjectService implements IProjectService {
    private ITaskRepository taskRepository;
    private IProjectRepository projectRepository;
    private  IProjectMapper projectMapper;
    public ProjectService(IProjectRepository projectRepository, IProjectMapper projectMapper,ITaskRepository taskRepository ){
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
}
