package com.projectmanagement.service.interfaces;

import com.projectmanagement.domain.model.GenericApiResponse;
import com.projectmanagement.service.DTO.AddTaskRequestDto;
import com.projectmanagement.service.DTO.TaskInfoDto;
import com.projectmanagement.service.DTO.UpdateTaskRequestDto;

import java.util.List;

public interface ITaskService {

    GenericApiResponse<String,String> addTask(AddTaskRequestDto addTaskRequestDto);

    GenericApiResponse<String,String> updateTask(UpdateTaskRequestDto updateTaskRequestDto);

    GenericApiResponse<String,String> deleteTask(Long id);
    public GenericApiResponse<String, List<TaskInfoDto>> getTasksByProjectId(Long projectId);

    public GenericApiResponse<String, TaskInfoDto>  getTask(Long taskId);

    public GenericApiResponse<String, List<TaskInfoDto>> getChildTasks(Long taskId);
}
