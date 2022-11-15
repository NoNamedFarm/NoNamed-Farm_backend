package com.nonamed.farm.domain.diary.exception;

import com.nonamed.farm.global.exception.ErrorCode;
import com.nonamed.farm.global.exception.NonamedException;

public class NotRightDateException extends NonamedException {

	public final static NotRightDateException EXCEPTION = new NotRightDateException();

	public NotRightDateException() {
		super(ErrorCode.NOT_RIGHT_DATE);
	}

}
