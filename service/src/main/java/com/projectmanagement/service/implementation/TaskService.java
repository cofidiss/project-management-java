package com.projectmanagement.service.implementation;

import com.projectmanagement.domain.model.GenericApiResponse;
import com.projectmanagement.repository.interfaces.IProjectRepository;
import com.projectmanagement.repository.interfaces.ITaskRepository;
import com.projectmanagement.service.DTO.AddTaskRequestDto;
import com.projectmanagement.service.interfaces.ITaskService;
import com.projectmanagement.service.mapper.IProjectMapper;
import com.projectmanagement.service.mapper.ITaskMapper;

public class TaskService implements ITaskService {
    private IProjectRepository projectRepository;
    private ITaskRepository taskRepository;
    private ITaskMapper taskMapper;
    public TaskService(ITaskRepository taskRepository,IProjectRepository projectRepository,ITaskMapper taskMapper){
        this.taskMapper = taskMapper;
        this.taskRepository  = taskRepository;
        this.projectRepository = projectRepository;
    }
    @Override
    public GenericApiResponse<String, String> addTask(AddTaskRequestDto addTaskRequestDto) {
        var genericApiResponse = new GenericApiResponse<String, String>();

          var optProject =   projectRepository.findById(addTaskRequestDto.getProjectId());

          if(optProject.isEmpty()){
              genericApiResponse.setStatusCode(404);
              genericApiResponse.setErrorData("project not found");
              return  genericApiResponse;
          }

        return genericApiResponse;
    }
}
