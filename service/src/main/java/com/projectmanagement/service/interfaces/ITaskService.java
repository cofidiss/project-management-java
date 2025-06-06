package com.projectmanagement.service.interfaces;

import com.projectmanagement.domain.model.GenericApiResponse;
import com.projectmanagement.service.DTO.AddTaskRequestDto;
import com.projectmanagement.service.DTO.UpdateTaskRequestDto;

public interface ITaskService {

    GenericApiResponse<String,String> addTask(AddTaskRequestDto addTaskRequestDto);

    GenericApiResponse<String,String> updateTask(UpdateTaskRequestDto updateTaskRequestDto);

    GenericApiResponse<String,String> deleteTask(Long id);
}
