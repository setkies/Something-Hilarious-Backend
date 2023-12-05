package com.setkies.sinp.domain.user.service;

import com.setkies.sinp.domain.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {
    private final UserRepo userRepo;
    private final UserService userService;



}
