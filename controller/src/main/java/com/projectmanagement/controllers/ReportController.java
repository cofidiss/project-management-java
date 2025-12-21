package com.projectmanagement.controllers;

import com.projectmanagement.controller.annotaiton.RequirePermission;
import com.projectmanagement.domain.enums.RoleEnum;
import com.projectmanagement.service.DTO.LoginRequestDto;
import com.projectmanagement.service.DTO.SignUpRequestDto;
import com.projectmanagement.service.interfaces.IReportService;
import com.projectmanagement.service.interfaces.IUserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    private final IReportService reportService;

    public ReportController(IReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/summary/projectStatus")
    public ResponseEntity<?> projectStatus( ) {

        var  result = reportService.getProjectStatus();
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }
        return response.body(result.getErrorData());
    }
    @GetMapping("/summary/projectManHour")
    public ResponseEntity<?> projectManHour( ) {
        var  result = reportService.getProjectManHour();
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }
        return response.body(result.getErrorData());
    }
    @GetMapping("/summary/projectTask")
    public ResponseEntity<?> projectTask(  ) {
        var  result = reportService.getProjectTask();
        var response =   ResponseEntity.status( result.getStatusCode());
        if (result.isSuccess()) {

            return response.body(result.getSuccessData());
        }
        return response.body(result.getErrorData());
    }
}

