package com.nonamed.farm.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NonamedException extends RuntimeException {

	private final ErrorCode errorCode;

}
