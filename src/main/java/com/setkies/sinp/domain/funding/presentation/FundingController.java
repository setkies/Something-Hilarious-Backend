package com.setkies.sinp.domain.funding.presentation;

import com.setkies.sinp.domain.funding.Funding;
import com.setkies.sinp.domain.funding.dto.FundReq;
import com.setkies.sinp.domain.funding.service.FundingDef;
import com.setkies.sinp.domain.funding.service.FundingRead;
import com.setkies.sinp.infrastructure.security.util.SecurityUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/funding")
public class FundingController {
    private final FundingDef fundingDef;
    private final FundingRead fundingRead;

    @PostMapping
    public Long doFund(@RequestBody FundReq fundReq){
        return fundingDef.doFund(fundReq, SecurityUtil.getCurrentUserWithLogin());
    }

    @GetMapping("/me")
    public List<Funding> getMyfund(){
        return fundingRead.findMyFund(SecurityUtil.getCurrentUserWithLogin());
    }

    @GetMapping("/project/{id}")
    public List<Funding> getProjectFund(@PathVariable Long id){
        return fundingRead.findProjectFund(id,SecurityUtil.getCurrentUserWithLogin());
    }

}
