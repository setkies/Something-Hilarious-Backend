package com.setkies.sinp.infrastructure.jwt.auth;

import com.setkies.sinp.global.error.exception.ErrorCode;
import com.setkies.sinp.global.error.exception.SnipException;
import com.setkies.sinp.infrastructure.jwt.properties.JwtConstants;
import com.setkies.sinp.infrastructure.jwt.util.JwtUtil;
import com.setkies.sinp.infrastructure.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuth {
    private final JwtUtil jwtUtil;
    private final AuthDetailsService authDetailsService;

    public Authentication authentication(String token) {
        Claims claims = jwtUtil.getJwt(token).getBody();

        if (isNotAccessToken(token)) {
            throw new SnipException(ErrorCode.INVALID_TOKEN);
        }

        UserDetails userDetails = authDetailsService.loadUserByUsername(claims.get(JwtConstants.AUTH_ID.message).toString());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private boolean isNotAccessToken(String token) {
        if (token.isEmpty()) {
            throw new SnipException(ErrorCode.INVALID_TOKEN);
        }
        String role = jwtUtil.getJwt(token).getHeader().get(JwtConstants.TYPE.message).toString();
        return !role.equals(JwtConstants.ACCESS_KEY.message);
    }
}
