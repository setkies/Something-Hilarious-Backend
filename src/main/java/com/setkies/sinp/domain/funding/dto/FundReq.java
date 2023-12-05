package com.setkies.sinp.domain.funding.dto;

import jakarta.validation.constraints.Positive;
import jdk.jfr.Unsigned;
import lombok.Getter;

@Getter
public class FundReq {
    private Long projectId;
    @Positive
    private Long Money;
}
