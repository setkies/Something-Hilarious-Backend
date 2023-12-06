package com.setkies.sinp.global.error;

import com.setkies.sinp.global.error.exception.ErrorCode;
import com.setkies.sinp.global.error.exception.SnipException;
import com.setkies.sinp.global.response.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (SnipException e) {
            writeErrorCode(response, e.getErrorCode());
        } catch (ExpiredJwtException e) {
            writeErrorCode(response, ErrorCode.EXPIRED_JWT);
        } catch (JwtException e) {
            writeErrorCode(response, ErrorCode.INVALID_TOKEN);
        } catch (Exception e) {
            e.printStackTrace();
            writeErrorCode(response, ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private void writeErrorCode(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(
                errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage()
        );

        response.setStatus(errorResponse.status());
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(errorResponse.toString());
    }
}

