package com.setkies.sinp.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SnipException extends RuntimeException {

    private final ErrorCode errorCode;
}
