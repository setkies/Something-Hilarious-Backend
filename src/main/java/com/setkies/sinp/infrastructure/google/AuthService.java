package com.setkies.sinp.infrastructure.google;

import com.setkies.sinp.domain.user.Authority;
import com.setkies.sinp.domain.user.User;
import com.setkies.sinp.domain.user.repo.UserRepo;
import com.setkies.sinp.global.config.properties.AuthProperties;
import com.setkies.sinp.infrastructure.google.feign.GoogleAuthClient;
import com.setkies.sinp.infrastructure.google.feign.GoogleInfoClient;
import com.setkies.sinp.infrastructure.google.feign.dto.request.GoogleTokenRequest;
import com.setkies.sinp.infrastructure.google.feign.dto.response.GoogleInfoResponse;
import com.setkies.sinp.infrastructure.jwt.auth.JwtAuth;
import com.setkies.sinp.infrastructure.jwt.dto.TokenResponse;
import com.setkies.sinp.infrastructure.jwt.util.JwtProvider;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtProvider jwtProvider;
    private final JwtAuth jwtAuth;

    public TokenResponse execute(String refreshToken) {
        User user = jwtAuth.getUserEmail(refreshToken);
        return jwtProvider.generateToken(user.getEmail(), user.getAuthority().toString());
    }

}
