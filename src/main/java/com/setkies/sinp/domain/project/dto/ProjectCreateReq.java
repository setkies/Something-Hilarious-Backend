package com.setkies.sinp.domain.project.dto;

import com.setkies.sinp.domain.project.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ProjectCreateReq {
    private String name;
    private String summary;
    private String thumbnail;
    private String introduceUrl;
    private Long targetFund;
    private LocalDateTime fundEndTime;
}
