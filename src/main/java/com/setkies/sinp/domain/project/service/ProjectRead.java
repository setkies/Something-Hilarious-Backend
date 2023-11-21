package com.setkies.sinp.domain.project.service;

import com.setkies.sinp.domain.project.Project;
import com.setkies.sinp.domain.project.dto.ProjectRes;
import com.setkies.sinp.domain.project.repo.ProjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectRead {
    private final ProjectRepo projectRepo;

    public Page<ProjectRes> getProjectList(Pageable pageable){
        return projectRepo.findAll(pageable).map(ProjectRes::new);
    }
    public ProjectRes getProject(Long id){
        Project project = projectRepo.findById(id).orElseThrow();
        return new ProjectRes(project);
    }

}
