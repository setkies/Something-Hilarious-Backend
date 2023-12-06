package com.setkies.sinp.domain.project.service;

import com.setkies.sinp.domain.project.Project;
import com.setkies.sinp.domain.project.Status;
import com.setkies.sinp.domain.project.dto.ProjectRes;
import com.setkies.sinp.domain.project.repo.ProjectRepo;
import com.setkies.sinp.domain.user.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectRead {
    private final ProjectRepo projectRepo;

    public Page<ProjectRes> getProjectList(String status,Pageable pageable){
        Status S = switch (status){
            case "END", "end" -> Status.END;
            case "PENDING", "pending" -> Status.PENDING;
            default -> Status.PROCESS;
        };

        return projectRepo.findByStatus(S,pageable).map(ProjectRes::new);
    }
    public ProjectRes getProject(Long id){
        Project project = projectRepo.findById(id).orElseThrow();
        return new ProjectRes(project);
    }
    
    public List<ProjectRes> getMyProject(User user){
        return projectRepo.findByAuthor(user).stream().map(ProjectRes::new)
                .toList();
        
    }

}
