package com.projectit.project_service.service;
import com.projectit.project_service.domain.Category;
import com.projectit.project_service.domain.Project;
import com.projectit.project_service.dto.ProjectRequestDTO;
import com.projectit.project_service.repository.CategoryRepo;
import com.projectit.project_service.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  ProjectServiceImpl implements ProjectService {

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

    @Override
    public Project createProject(ProjectRequestDTO projectDTO) {
        if (projectDTO == null) {
            throw new IllegalArgumentException("Project DTO cannot be null");
        }

        Project project = new Project();
        project.setTitle(projectDTO.getTitle());
        project.setDescription(projectDTO.getDescription());
        project.setOwnerUserId(projectDTO.getOwnerUserId());

        Category category = handleCategory(projectDTO);
        project.setCategory(category);

        return projetRepository.save(project);
    }

    @Override
    public Project updateProject(Long id, ProjectRequestDTO projectDTO) {
        if (id == null) {
            throw new IllegalArgumentException("Project ID cannot be null");
        }
        if (projectDTO == null) {
            throw new IllegalArgumentException("Project DTO cannot be null");
        }

        Project existingProject = projetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        existingProject.setTitle(projectDTO.getTitle());
        existingProject.setDescription(projectDTO.getDescription());

        Category category = handleCategory(projectDTO);
        existingProject.setCategory(category);

        return projetRepository.save(existingProject);
    }

    private Category handleCategory(ProjectRequestDTO projectDTO) {
        if (projectDTO.getNewCategoryName() != null && !projectDTO.getNewCategoryName().trim().isEmpty()) {
            // Vérifier si la catégorie existe déjà
            String categoryName = projectDTO.getNewCategoryName().trim();
            return categoryRepo.findByName(categoryName)
                    .orElseGet(() -> {
                        Category newCategory = new Category();
                        newCategory.setName(categoryName);
                        return categoryRepo.save(newCategory);
                    });
        } else if (projectDTO.getCategoryId() != null) {
            // Utiliser catégorie existante
            return categoryRepo.findById(projectDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
        } else {
            throw new IllegalArgumentException("Either categoryId or newCategoryName must be provided");
        }
    }


}
