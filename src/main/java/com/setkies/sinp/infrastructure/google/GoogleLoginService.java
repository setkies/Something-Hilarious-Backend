package com.setkies.sinp.infrastructure.google;

import com.setkies.sinp.domain.user.Authority;
import com.setkies.sinp.domain.user.User;
import com.setkies.sinp.domain.user.repo.UserRepo;
import com.setkies.sinp.global.config.properties.AuthProperties;
import com.setkies.sinp.infrastructure.google.feign.GoogleAuthClient;
import com.setkies.sinp.infrastructure.google.feign.GoogleInfoClient;
import com.setkies.sinp.infrastructure.google.feign.dto.request.GoogleTokenRequest;
import com.setkies.sinp.infrastructure.google.feign.dto.response.GoogleInfoResponse;
import com.setkies.sinp.infrastructure.jwt.dto.TokenResponse;
import com.setkies.sinp.infrastructure.jwt.util.JwtProvider;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleLoginService {

    private final AuthProperties authProperties;
    private final GoogleAuthClient googleAuthClient;
    private final GoogleInfoClient googleInfoClient;
    private final JwtProvider jwtProvider;
    private final UserRepo userRepo;

    public TokenResponse execute(String code) {
        String googleToken = googleAuthClient.getGoogleToken(
                createRequest(code)
        ).accessToken();
        GoogleInfoResponse userInfo = googleInfoClient.getUserInfo(googleToken);
        User user = saveOrUpdate(userInfo);

        return jwtProvider.generateToken(user.getEmail(), user.getAuthority().toString());
    }

    public GoogleTokenRequest createRequest(String code) {
        return GoogleTokenRequest.builder()
                .code(code)
                .clientId(authProperties.getClientId())
                .clientSecret(authProperties.getClientSecret())
                .redirectUri(authProperties.getRedirectUri())
                .grantType("authorization_code")
                .build();
    }

    private User saveOrUpdate(GoogleInfoResponse response) {
        Optional<User> user = userRepo.findByEmail(response.email());

        if (user.isEmpty()) {
            return userRepo.save(User.builder()
                    .email(response.email())
                    .name(response.name())
                    .authority(Authority.USER)
                    .profileImage(response.picture())
                    .build());
        }

        return user.get().update(response);
    }
}
