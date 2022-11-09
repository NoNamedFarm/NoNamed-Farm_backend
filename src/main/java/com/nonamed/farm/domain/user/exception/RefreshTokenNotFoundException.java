package com.nonamed.farm.domain.user.exception;

import com.nonamed.farm.global.exception.ErrorCode;
import com.nonamed.farm.global.exception.NonamedException;

public class RefreshTokenNotFoundException extends NonamedException {

	public final static RefreshTokenNotFoundException EXCEPTION = new RefreshTokenNotFoundException();

	public RefreshTokenNotFoundException() {
		super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
	}

}
