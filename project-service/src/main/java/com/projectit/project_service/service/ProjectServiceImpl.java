package com.projectit.project_service.service;

import com.projectit.project_service.domain.Category;
import com.projectit.project_service.domain.Project;
import com.projectit.project_service.repository.CategoryRepo;
import com.projectit.project_service.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private  final ProjetRepository projetRepository;

    @Autowired
    private  final CategoryRepo categoryRepo;

    public ProjectServiceImpl(ProjetRepository projetRepository ,
                              CategoryRepo categoryRepo) {
        this.projetRepository = projetRepository;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Project createProject(Project project ) {

        Long categoryId = project.getCategory().getId();
        Category category = categoryRepo.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("Category not found"));

        project.setCategory(category);
        return projetRepository.save(project);

    }

    @Override
    public Project updateProject(Long idproject, Project updatedproject ) {

        Project existingproject = projetRepository.findById(idproject)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        existingproject.setDescription(updatedproject.getDescription());
        existingproject.setTitle(updatedproject.getTitle());
        existingproject.setDescription(updatedproject.getDescription());
        Long categoryId = updatedproject.getCategory().getId();

        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        existingproject.setCategory(updatedproject.getCategory());

        return projetRepository.save(existingproject);

    }

    @Override
    public void deleteProject(Long id) {

      Project project = projetRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Project not found"));

      projetRepository.delete(project);

    }

    @Override
    public List<Project> getProjectsByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return projetRepository.findByOwnerUserId(userId);
    }

    @Override
    public List<Project> searchProjectsByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        return projetRepository.findByTitleContainingIgnoreCase(title.trim());
    }

    @Override
    public List<Project> filterProjectsByCategory(Long categoryId) {
        if (categoryId == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }
        return projetRepository.findByCategoryId(categoryId);
    }


}
