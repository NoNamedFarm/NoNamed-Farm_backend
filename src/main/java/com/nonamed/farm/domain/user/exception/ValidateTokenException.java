package com.nonamed.farm.domain.user.exception;

import com.nonamed.farm.global.exception.ErrorCode;
import com.nonamed.farm.global.exception.NonamedException;

public class ValidateTokenException extends NonamedException {

	public final static ValidateTokenException EXCEPTION = new ValidateTokenException();

	public ValidateTokenException() {
		super(ErrorCode.VALIDATE_TOKEN);
	}

}
