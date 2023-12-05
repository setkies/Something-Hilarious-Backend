package com.setkies.sinp.domain.project.service;

import com.setkies.sinp.domain.project.Project;
import com.setkies.sinp.domain.project.Status;
import com.setkies.sinp.domain.project.dto.ProjectCreateReq;
import com.setkies.sinp.domain.project.repo.ProjectRepo;
import com.setkies.sinp.domain.user.User;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectDef {
    private final ProjectRepo projectRepo;

    public Long createProject(ProjectCreateReq projectCreateReq, User user){
        Project newProject = Project.builder()
                .status(Status.PENDING)
                .fundEndTime(projectCreateReq.getFundEndTime())
                .introduceUrl(projectCreateReq.getIntroduceUrl())
                .summary(projectCreateReq.getSummary())
                .targetFund(projectCreateReq.getTargetFund())
                .thumbnail(projectCreateReq.getThumbnail())
                .name(projectCreateReq.getName())
                .author(user)
                .build();

        return projectRepo.save(newProject).getId();
    }

    public Long updateProject(Long id, ProjectCreateReq projectCreateReq, User user){
        Project project = projectRepo.findById(id).orElseThrow();

        if(!Objects.equals(project.getAuthor().getId(), user.getId())){
            throw new RuntimeException();
        }

        project.updateProject(projectCreateReq);
        return project.getId();
    }

    public Long changeProjectStatus(Long id, Status status, User user){
        Project project = projectRepo.findById(id).orElseThrow();

        if(!Objects.equals(project.getAuthor().getId(), user.getId())){
            throw new RuntimeException();
        }
        project.changeProjectStatus(status);
        return project.getId();
    }

    public Long deleteProject(Long id,User user){
        Project project = projectRepo.findById(id).orElseThrow();

        if(!Objects.equals(project.getAuthor().getId(), user.getId())){
            throw new RuntimeException();
        }
        projectRepo.delete(project);
        return project.getId();
    }
}
