package com.projectmanagement.controllers;

import com.projectmanagement.service.DTO.AddProjectRequestDto;
import com.projectmanagement.service.DTO.AddTaskRequestDto;
import com.projectmanagement.service.DTO.UpdateTaskRequestDto;
import com.projectmanagement.service.interfaces.ITaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
public class TaskController {


    private final ITaskService taskService;

    public TaskController(ITaskService taskService) {

        this.taskService = taskService;
    }
    @PostMapping("/addTask")
    public ResponseEntity<?> addTask(@RequestBody AddTaskRequestDto addTaskRequestDto){

        var  result = taskService.addTask(addTaskRequestDto);
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }
    @PostMapping("/updateTask")
    public ResponseEntity<?> updateTask(@RequestBody UpdateTaskRequestDto updateTaskRequestDto){

        var  result = taskService.updateTask(updateTaskRequestDto);
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }
    @DeleteMapping("/deleteTask")
    public ResponseEntity<?> deleteTask( Long id){

        var  result = taskService.deleteTask(id);
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }
}
