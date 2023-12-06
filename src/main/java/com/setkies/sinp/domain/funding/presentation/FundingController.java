package com.setkies.sinp.domain.funding.presentation;

import com.setkies.sinp.domain.funding.service.FundingDef;
import com.setkies.sinp.domain.funding.service.FundingRead;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/funding")
public class FundingController {
    private final FundingDef fundingDef;
    private final FundingRead fundingRead;
}
