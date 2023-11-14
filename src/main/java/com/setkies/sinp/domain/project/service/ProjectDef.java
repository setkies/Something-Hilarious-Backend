package com.setkies.sinp.domain.project.service;

import com.setkies.sinp.domain.project.Project;
import com.setkies.sinp.domain.project.Status;
import com.setkies.sinp.domain.project.dto.ProjectCreateReq;
import com.setkies.sinp.domain.project.repo.ProjectRepo;
import com.setkies.sinp.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectDef {
    private final ProjectRepo projectRepo;

    public void createProject(ProjectCreateReq projectCreateReq, User user){
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

        projectRepo.save(newProject);
    }
}
