package com.nonamed.farm.global.jwt.exception;

import com.nonamed.farm.global.exception.ErrorCode;
import com.nonamed.farm.global.exception.NonamedException;

public class TokenUnauthorizedException extends NonamedException {

	public final static TokenUnauthorizedException EXCEPTION = new TokenUnauthorizedException();

	public TokenUnauthorizedException() {
		super(ErrorCode.TOKEN_UNAUTHORIZED);
	}

}
