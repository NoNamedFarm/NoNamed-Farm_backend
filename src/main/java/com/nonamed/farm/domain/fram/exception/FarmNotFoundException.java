package com.nonamed.farm.domain.fram.exception;

import com.nonamed.farm.global.exception.ErrorCode;
import com.nonamed.farm.global.exception.NonamedException;

public class FarmNotFoundException extends NonamedException {

	public final static FarmNotFoundException EXCEPTION = new FarmNotFoundException();

	public FarmNotFoundException() {
		super(ErrorCode.FARM_NOT_FOUND);
	}

}
