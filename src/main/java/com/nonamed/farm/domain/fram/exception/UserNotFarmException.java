package com.nonamed.farm.domain.fram.exception;

import com.nonamed.farm.global.exception.ErrorCode;
import com.nonamed.farm.global.exception.NonamedException;

public class UserNotFarmException extends NonamedException {

	public final static UserNotFarmException EXCEPTION = new UserNotFarmException();

	public UserNotFarmException() {
		super(ErrorCode.USER_NOT_FARM);
	}

}
