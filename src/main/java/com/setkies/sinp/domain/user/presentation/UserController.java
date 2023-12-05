package com.setkies.sinp.domain.user.presentation;

import com.setkies.sinp.domain.funding.service.FundingDef;
import com.setkies.sinp.domain.funding.service.FundingRead;
import com.setkies.sinp.domain.user.User;
import com.setkies.sinp.domain.user.dto.UserSimpleRes;
import com.setkies.sinp.domain.user.service.UserService;
import com.setkies.sinp.infrastructure.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserSimpleRes getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("")
    public User getUser() {
        return SecurityUtil.getCurrentUserWithLogin();
    }
}
