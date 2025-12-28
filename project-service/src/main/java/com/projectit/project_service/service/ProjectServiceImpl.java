package com.projectit.project_service.service;

import com.projectit.project_service.domain.Category;
import com.projectit.project_service.domain.Project;
import com.projectit.project_service.repository.CategoryRepo;
import com.projectit.project_service.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
