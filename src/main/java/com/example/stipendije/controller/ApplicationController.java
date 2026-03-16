package com.example.stipendije.controller;

import com.example.stipendije.dto.ApplicationRankingDTO;
import com.example.stipendije.model.entity.Application;
import com.example.stipendije.service.ApplicationRankingService;
import com.example.stipendije.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Applications", description = "Management of scholarship applications")
@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationRankingService applicationRankingService;
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationRankingService applicationRankingService,
                                 ApplicationService applicationService) {
        this.applicationRankingService = applicationRankingService;
        this.applicationService = applicationService;
    }

    @Operation(
            summary = "Create a new application",
            description = "Creates a new scholarship application for the specified academic year for the currently logged-in student."
    )
    @ApiResponse(responseCode = "201", description = "Application successfully created")
    @ApiResponse(responseCode = "400", description = "Invalid academic year or application already exists")
    @PostMapping
    public ResponseEntity<Application> createApplication(@RequestParam String academicYear) {
        Application savedApp = applicationService.createApplication(academicYear);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedApp);
    }

    @Operation(
            summary = "Get scholarship ranking",
            description = "Returns a list of students ranked by their total points for a specific academic year."
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the ranking list")
    @GetMapping("/ranking/{academicYear}")
    public List<ApplicationRankingDTO> getRanking(@PathVariable String academicYear) {
        return applicationRankingService.getRankingForYear(academicYear);
    }
}
