package com.projectit.project_service.controllers;

import com.projectit.project_service.domain.Category;
import com.projectit.project_service.domain.Project;
import com.projectit.project_service.repository.CategoryRepo;
import com.projectit.project_service.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private final ProjectService projectService;


    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return new ResponseEntity(createdProject, HttpStatus.CREATED);
    }


}
