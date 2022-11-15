package com.nonamed.farm.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	TOKEN_EXPIRATION(401,"토큰이 만료 되었습니다."),
	TOKEN_UNAUTHORIZED(401,"토큰이 유효 하지 않습니다."),
	VALIDATE_TOKEN(409,"토큰이 유효 합니다."),
	NOT_REFRESH_TOKEN(401,"refresh 토큰이 아닙니다."),
	REFRESH_TOKEN_NOT_FOUND(401,"refresh 토큰을 찾을 수 없습니다."),

	NOT_MATCHES_PASSWORD(400, "비밀번호가 올바르지 않습니다."),
	USER_NOT_FOUND(404, "유저를 찾을 수 없습니다."),
	USER_ID_OVERLAP_EXIST(409,"아이디가 이미 존재합니다."),

	FARM_NOT_FOUND(404, "농장을 찾을 수 없습니다."),
	DEVICE_NOT_FOUND(404, "농장 device를 찾을 수 없습니다."),
	USER_NOT_FARM(401, "농장 주인이 아닙니다."),

	DIARY_NOT_FOUND(404, "일지를 찾을 수 없습니다."),
	USER_NOT_DIARY(401, "일기 작성자가 아닙니다."),
	NOT_RIGHT_DATE(400, "날짜를 다시 확인해주세요.");

	private final int status;
	private final String message;

}
