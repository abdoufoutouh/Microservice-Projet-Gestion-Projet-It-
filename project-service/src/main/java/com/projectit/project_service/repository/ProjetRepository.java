package com.projectit.project_service.repository;

import com.projectit.project_service.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetRepository  extends JpaRepository<Project, Long> {


}
