package com.projectmanagement.controllers;

import com.projectmanagement.service.interfaces.IReportService;
import org.springframework.http.ResponseEntity;
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

