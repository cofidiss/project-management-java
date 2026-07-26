package com.projectmanagement.controllers;

import com.projectmanagement.controller.annotaiton.RequirePermission;
import com.projectmanagement.domain.enums.ProjectStatusEnum;
import com.projectmanagement.domain.enums.RoleEnum;
import com.projectmanagement.domain.enums.TaskStatusEnum;
import com.projectmanagement.service.DTO.AddProjectRequestDto;
import com.projectmanagement.service.DTO.AddTaskRequestDto;
import com.projectmanagement.service.DTO.UpdateTaskRequestDto;
import com.projectmanagement.service.interfaces.IProjectService;
import com.projectmanagement.service.interfaces.ITaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
public class TaskController {


    private final ITaskService taskService;
    private final IProjectService projectService;

    public TaskController(ITaskService taskService, IProjectService projectService) {

        this.taskService = taskService;
        this.projectService = projectService;
    }
    @GetMapping("/")
    public ResponseEntity<?> getTask( Long taskId){

        var  result = taskService.getTask(taskId);
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }
    @RequirePermission(RoleEnum.MODIFY)
    @PostMapping("/")
    public ResponseEntity<?> addTask(@RequestBody AddTaskRequestDto addTaskRequestDto){

        var  result = taskService.addTask(addTaskRequestDto);
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }
    @RequirePermission(RoleEnum.MODIFY)
    @PutMapping("/")
    public ResponseEntity<?> updateTask(@RequestBody UpdateTaskRequestDto updateTaskRequestDto){

        var  result = taskService.updateTask(updateTaskRequestDto);
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }
    @RequirePermission(RoleEnum.MODIFY)
    @DeleteMapping("/")
    public ResponseEntity<?> deleteTask( Long id){

        var  result = taskService.deleteTask(id);
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }
    @GetMapping("/getStatusLov")
    public ResponseEntity<?> getStatusLov( ){
        var  result = projectService.getLovDto(TaskStatusEnum.class);
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }
    @GetMapping("/getTasksByProjectId")
    public ResponseEntity<?> getTasksByProjectId(Long projectId  ){
        var  result = taskService.getTasksByProjectId(projectId);
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }

    @GetMapping("/getChildTasks")
    public ResponseEntity<?> getChildTasks(Long parentTaskId  ){
        var  result = taskService.getChildTasks(parentTaskId);
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }

}
