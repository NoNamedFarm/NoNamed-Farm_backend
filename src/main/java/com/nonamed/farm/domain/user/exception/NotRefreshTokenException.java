package com.nonamed.farm.domain.user.exception;

import com.nonamed.farm.global.exception.ErrorCode;
import com.nonamed.farm.global.exception.NonamedException;

public class NotRefreshTokenException extends NonamedException {

	public final static NotRefreshTokenException EXCEPTION = new NotRefreshTokenException();

	public NotRefreshTokenException() {
		super(ErrorCode.NOT_REFRESH_TOKEN);
	}

}
