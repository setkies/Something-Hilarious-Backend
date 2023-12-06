package com.setkies.sinp.domain.funding.service;

import com.setkies.sinp.domain.funding.Funding;
import com.setkies.sinp.domain.funding.dto.FundReq;
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
public class FundingDef {
    private final FundingRepo fundingRepo;
    private final ProjectRepo projectRepo;

    public Long doFund(FundReq fundReq, User user) {
        Project project = projectRepo.findById(fundReq.getProjectId())
                .orElseThrow();

        Funding funding = Funding.builder()
                .project(project)
                .user(user)
                .money(fundReq.getMoney())
                .build();

        fundingRepo.save(funding);
        project.changeProjectFund(fundReq.getMoney());

        return funding.getId();

    }


}
