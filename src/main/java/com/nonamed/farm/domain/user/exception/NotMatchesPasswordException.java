package com.nonamed.farm.domain.user.exception;

import com.nonamed.farm.global.exception.ErrorCode;
import com.nonamed.farm.global.exception.NonamedException;

public class NotMatchesPasswordException extends NonamedException {

	public final static NotMatchesPasswordException EXCEPTION = new NotMatchesPasswordException();

	public NotMatchesPasswordException() {
		super(ErrorCode.NOT_MATCHES_PASSWORD);
	}

}
