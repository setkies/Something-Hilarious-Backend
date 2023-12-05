package com.setkies.sinp.infrastructure.security.auth;

import com.setkies.sinp.domain.user.repo.UserRepo;
import com.setkies.sinp.global.error.exception.ErrorCode;
import com.setkies.sinp.global.error.exception.SnipException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AuthDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email)
                .map(AuthDetails::new)
                .orElseThrow(() -> new SnipException(ErrorCode.USER_NOT_FOUND));
    }
}