package com.setkies.sinp.domain.project.dto;

import com.setkies.sinp.domain.project.Project;
import com.setkies.sinp.domain.project.Status;
import com.setkies.sinp.domain.user.dto.UserSimpleRes;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ProjectRes {
    private final Long id;
    private final String name;
    private final String summary;
    private final String thumbnail;
    private final String introduceUrl;
    private final Status status;
    private final Long targetFund;
    private final LocalDateTime fundEndTime;
    private final UserSimpleRes author;

    public ProjectRes(Project project){
        this.id = project.getId();
        this.fundEndTime = project.getFundEndTime();
        this.introduceUrl = project.getIntroduceUrl();
        this.status = project.getStatus();
        this.summary = project.getSummary();
        this.targetFund = project.getTargetFund();
        this.thumbnail = project.getThumbnail();
        this.name = project.getName();
        this.author = new UserSimpleRes(project.getAuthor());
    }
}
