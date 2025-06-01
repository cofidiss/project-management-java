package com.projectmanagement.controllers;

import com.projectmanagement.service.DTO.AddProjectRequestDto;
import com.projectmanagement.service.DTO.UpdateProjectRequestDto;
import com.projectmanagement.service.interfaces.IProjectService;
import com.projectmanagement.service.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/addProject")
    public ResponseEntity<?> addProject(@RequestBody AddProjectRequestDto AddProjectRequestDto){

     var  result = projectService.addProject(AddProjectRequestDto);
      var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }
    @PostMapping("/updateProject")
    public ResponseEntity<?> updateProject(@RequestBody UpdateProjectRequestDto updateProjectRequestDto){

        var  result = projectService.updateProject(updateProjectRequestDto);
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }
    @PostMapping("/deleteProject")
    public ResponseEntity<?> deleteProject(@RequestBody long id){
return ResponseEntity.ok("");
    }
}
