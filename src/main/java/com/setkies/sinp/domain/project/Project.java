package com.setkies.sinp.domain.project;

import com.setkies.sinp.domain.project.dto.ProjectCreateReq;
import com.setkies.sinp.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String summary;
    private String thumbnail;
    private String introduceUrl;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Long targetFund;
    private LocalDateTime fundEndTime;
    private Long nowFund = 0L;

    @ManyToOne
    private User author;

    public void updateProject(ProjectCreateReq projectCreateReq){
        this.fundEndTime = projectCreateReq.getFundEndTime();
        this.introduceUrl = projectCreateReq.getIntroduceUrl();
        this.summary = projectCreateReq.getSummary();
        this.targetFund = projectCreateReq.getTargetFund();
        this.thumbnail = projectCreateReq.getThumbnail();
        this.name = projectCreateReq.getName();
    }

    public void changeProjectStatus(Status status){
        this.status = status;
    }

}
