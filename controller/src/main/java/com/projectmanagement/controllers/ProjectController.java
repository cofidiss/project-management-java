package com.projectmanagement.controllers;

import com.projectmanagement.domain.enums.ProjectCategoryEnum;
import com.projectmanagement.domain.enums.ProjectStatusEnum;
import com.projectmanagement.service.DTO.AddProjectRequestDto;
import com.projectmanagement.service.DTO.FilterProjectDto;
import com.projectmanagement.service.DTO.UpdateProjectRequestDto;
import com.projectmanagement.service.interfaces.IProjectService;
import com.projectmanagement.service.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }
    @GetMapping("/")
    public ResponseEntity<?> getProject( long projectId){

        var  result = projectService.getProject(projectId);
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }

    @GetMapping("/detail")
    public ResponseEntity<?> getProjectDetail( long projectId){

        var  result = projectService.getProject(projectId);
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }
    @PostMapping("/")
    public ResponseEntity<?> addProject(@RequestBody AddProjectRequestDto AddProjectRequestDto){

     var  result = projectService.addProject(AddProjectRequestDto);
      var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }
    @PutMapping("/")
    public ResponseEntity<?> updateProject(@RequestBody UpdateProjectRequestDto updateProjectRequestDto){

        var  result = projectService.updateProject(updateProjectRequestDto);
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }
    @DeleteMapping("/")
    public ResponseEntity<?> deleteProject(long id){
        var  result = projectService.deleteProject(id);
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }
    @GetMapping("/FilterProject")
    public ResponseEntity<?> FilterProject(FilterProjectDto filterProjectDto){
        var  result = projectService.FilterProject(filterProjectDto);
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }

    @GetMapping("/getCategoryLov")
    public ResponseEntity<?> getCategoryLov( ){
        var  result = projectService.getLovDto(ProjectCategoryEnum.class);
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }

    @GetMapping("/getStatusLov")
    public ResponseEntity<?> getStatusLov( ){
        var  result = projectService.getLovDto(ProjectStatusEnum.class);
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }

    @GetMapping("/getProjectLov")
    public ResponseEntity<?> getProjectLov( ){
        var  result = projectService.getProjectLov();
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }

        return response.body(result.getErrorData());
    }
}
