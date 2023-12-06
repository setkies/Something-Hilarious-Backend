package com.setkies.sinp.domain.user.service;

import com.setkies.sinp.domain.user.User;
import com.setkies.sinp.domain.user.dto.UserSimpleRes;
import com.setkies.sinp.domain.user.repo.UserRepo;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public UserSimpleRes getUser(Long id){
        return new UserSimpleRes(userRepo.findById(id).orElseThrow());
    }



}
