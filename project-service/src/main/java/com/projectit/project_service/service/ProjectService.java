package com.projectit.project_service.service;

import com.projectit.project_service.domain.Project;
import org.springframework.stereotype.Service;

@Service
public interface ProjectService {

    Project createProject(Project project) ;
}
