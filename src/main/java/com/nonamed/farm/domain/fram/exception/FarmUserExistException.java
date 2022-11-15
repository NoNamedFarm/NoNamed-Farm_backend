package com.nonamed.farm.domain.fram.exception;

import com.nonamed.farm.global.exception.ErrorCode;
import com.nonamed.farm.global.exception.NonamedException;

public class FarmUserExistException extends NonamedException {

	public final static FarmUserExistException EXCEPTION = new FarmUserExistException();

	public FarmUserExistException() {
		super(ErrorCode.FARM_USER_EXIST);
	}

}
