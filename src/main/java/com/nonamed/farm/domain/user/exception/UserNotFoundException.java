package com.nonamed.farm.domain.user.exception;

import com.nonamed.farm.global.exception.ErrorCode;
import com.nonamed.farm.global.exception.NonamedException;

public class UserNotFoundException extends NonamedException {

	public final static UserNotFoundException EXCEPTION = new UserNotFoundException();

	public UserNotFoundException() {
		super(ErrorCode.USER_NOT_FOUND);
	}

}
