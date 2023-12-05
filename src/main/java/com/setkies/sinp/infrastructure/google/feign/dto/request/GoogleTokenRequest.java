package com.setkies.sinp.infrastructure.google.feign.dto.request;

import lombok.Builder;

@Builder
public record GoogleTokenRequest(
        String code,
        String clientId,
        String clientSecret,
        String redirectUri,
        String grantType
) {}
