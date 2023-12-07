package com.setkies.sinp.domain.auth.presentation;

import com.setkies.sinp.domain.auth.dto.LoginDto;
import com.setkies.sinp.domain.auth.dto.RefreshAccessDto;
import com.setkies.sinp.infrastructure.google.AuthService;
import com.setkies.sinp.infrastructure.google.GoogleLoginService;
import com.setkies.sinp.infrastructure.jwt.auth.JwtAuth;
import com.setkies.sinp.infrastructure.jwt.dto.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final GoogleLoginService googleLoginService;
    private final AuthService authService;

    @PostMapping("/google")
    @Operation(summary = "구글 로그인")
    public TokenResponse loginOfGoogle(@Validated @RequestBody LoginDto loginDto) {
        return googleLoginService.execute(loginDto.code());
    }

    @PostMapping("/refresh")
    @Operation(summary = "엑세스 토큰 재발급")
    public TokenResponse refreshAccessToken(@RequestBody RefreshAccessDto refreshToken) {
        return authService.execute(refreshToken.refreshToken());
    }
}
