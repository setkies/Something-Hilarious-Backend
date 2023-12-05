package com.setkies.sinp.infrastructure.security.util;

import com.setkies.sinp.domain.user.User;
import com.setkies.sinp.global.error.exception.ErrorCode;
import com.setkies.sinp.global.error.exception.SnipException;
import com.setkies.sinp.infrastructure.security.auth.AuthDetails;
import java.util.Optional;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static User getCurrentUserWithLogin() {
        try {
            return getUser();
        } catch (ClassCastException e) {
            throw new SnipException(ErrorCode.USER_NOT_FOUND);
        }
    }

    public static User getCurrentUserOrNotLogin() {
        try {
            return getUser();
        } catch (Exception e) {
            return null;
        }
    }

    public static Optional<User> getOptUser() {
        return Optional.ofNullable(getCurrentUserOrNotLogin());
    }

    private static User getUser() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof String) {
            throw new SnipException(ErrorCode.USER_NOT_FOUND);
        }

        AuthDetails authDetails = (AuthDetails) principal;

        return authDetails.getUser();
    }
}
