package com.nonamed.farm.domain.fram.exception;

import com.nonamed.farm.global.exception.ErrorCode;
import com.nonamed.farm.global.exception.NonamedException;

public class CycleNotFoundException extends NonamedException {

	public final static CycleNotFoundException EXCEPTION = new CycleNotFoundException();

	public CycleNotFoundException() {
		super(ErrorCode.CYCLE_NOT_FOUND);
	}

}
