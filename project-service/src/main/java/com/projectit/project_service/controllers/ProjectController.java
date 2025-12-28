package com.projectit.project_service.controllers;

import com.projectit.project_service.domain.Category;
import com.projectit.project_service.domain.Project;
import com.projectit.project_service.repository.CategoryRepo;
import com.projectit.project_service.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private final ProjectService projectService;


    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Create Project
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return new ResponseEntity(createdProject, HttpStatus.CREATED);
    }


    // Update Project
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(
            @RequestBody Project project , @PathVariable Long id) {

        Project updatedProject = projectService.updateProject(id, project);
        return new ResponseEntity(updatedProject, HttpStatus.OK);

    }
    // DELETE PROJECT
  @DeleteMapping("/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity(HttpStatus.OK);
  }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Project>> getProjectsByUserId(@PathVariable Long userId) {
        List<Project> projects = projectService.getProjectsByUserId(userId);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchProjectsByTitle(@RequestParam String title) {
        List<Project> projects = projectService.searchProjectsByTitle(title);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // Filter Projects by Category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Project>> filterProjectsByCategory(@PathVariable Long categoryId) {
        List<Project> projects = projectService.filterProjectsByCategory(categoryId);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

}
