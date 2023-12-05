package com.setkies.sinp.infrastructure.jwt.util;

import static com.setkies.sinp.infrastructure.jwt.properties.JwtConstants.ACCESS_KEY;
import static com.setkies.sinp.infrastructure.jwt.properties.JwtConstants.AUTH_ID;
import static com.setkies.sinp.infrastructure.jwt.properties.JwtConstants.REFRESH_KEY;
import static com.setkies.sinp.infrastructure.jwt.properties.JwtConstants.ROLE;
import static com.setkies.sinp.infrastructure.jwt.properties.JwtConstants.TYPE;

import com.setkies.sinp.infrastructure.jwt.dto.TokenResponse;
import com.setkies.sinp.infrastructure.jwt.properties.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.ZonedDateTime;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private final JwtProperties jwtProperties;

    public String generateAccessToken(String authId, String role) {
        return generateToken(authId, role, ACCESS_KEY.getMessage(), jwtProperties.getAccessExp());
    }

    public TokenResponse generateToken(String authId, String role) {

        String accessToken = generateToken(authId, role, ACCESS_KEY.getMessage(), jwtProperties.getAccessExp());
        String refreshToken = generateToken(authId, role, REFRESH_KEY.getMessage(), jwtProperties.getRefreshExp());

        return new TokenResponse(accessToken, refreshToken, getExpiredTime());
    }


    private String generateToken(String authId, String role, String type, Long exp) {
        return Jwts.builder()
                .setHeaderParam(TYPE.message, type)
                .claim(ROLE.getMessage(), role)
                .claim(AUTH_ID.getMessage(), authId)
                .signWith(jwtProperties.getSecret(), SignatureAlgorithm.HS256)
                .setExpiration(
                        new Date(System.currentTimeMillis() + exp * 1000)
                )
                .compact();
    }

    public ZonedDateTime getExpiredTime() {
        return ZonedDateTime.now().plusSeconds(jwtProperties.getRefreshExp());
    }
}
