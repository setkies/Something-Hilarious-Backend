package com.setkies.sinp.domain.funding.service;

import com.setkies.sinp.domain.funding.Funding;
import com.setkies.sinp.domain.funding.repo.FundingRepo;
import com.setkies.sinp.domain.project.Project;
import com.setkies.sinp.domain.project.repo.ProjectRepo;
import com.setkies.sinp.domain.user.User;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FundingRead {
    private final FundingRepo fundingRepo;
    private final ProjectRepo projectRepo;

    public List<Funding> findMyFund(User user){
        return fundingRepo.findByUser(user);
    }

    public List<Funding> findProjectFund(Long projectId, User user){
        Project project = projectRepo.findById(projectId)
                .orElseThrow();

        if(!Objects.equals(project.getAuthor().getId(), user.getId())){
            throw new NullPointerException();
        }
        return fundingRepo.findByProject(project);
    }
}
