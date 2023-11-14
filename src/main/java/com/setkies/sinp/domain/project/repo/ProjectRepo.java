package com.setkies.sinp.domain.project.repo;

import com.setkies.sinp.domain.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Long> {
}
