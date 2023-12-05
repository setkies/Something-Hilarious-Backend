package com.setkies.sinp.domain.auth.presentation;

import com.setkies.sinp.infrastructure.google.GoogleLoginService;
import com.setkies.sinp.infrastructure.jwt.dto.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final GoogleLoginService googleLoginService;

    @GetMapping("/google")
    @Operation(summary = "구글 로그인")
    public TokenResponse loginOfGoogle(@Validated @RequestParam(name = "code") String code) {
        return googleLoginService.execute(code);
    }
}
