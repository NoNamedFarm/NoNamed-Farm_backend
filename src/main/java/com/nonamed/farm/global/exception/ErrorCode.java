package com.nonamed.farm.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	TOKEN_EXPIRATION(401,"토큰이 만료 되었습니다."),
	TOKEN_UNAUTHORIZED(401,"토큰이 유효 하지 않습니다."),

	PASSWORD_WRONG(400, "비밀번호가 올바르지 않습니다."),
	USER_NOT_FOUND(404, "유저를 찾을 수 없습니다."),
	USER_ALREADY_EXIST(409,"유저가 이미 존재합니다.");

	private final int status;
	private final String message;

}
