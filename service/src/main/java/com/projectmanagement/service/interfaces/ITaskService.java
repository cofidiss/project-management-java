package com.projectmanagement.service.interfaces;

import com.projectmanagement.domain.model.GenericApiResponse;
import com.projectmanagement.service.DTO.AddTaskRequestDto;

public interface ITaskService {

    GenericApiResponse<String,String> addTask(AddTaskRequestDto addTaskRequestDto);
}
