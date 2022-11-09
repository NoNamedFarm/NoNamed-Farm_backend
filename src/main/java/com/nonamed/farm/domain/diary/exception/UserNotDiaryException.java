package com.nonamed.farm.domain.diary.exception;

import com.nonamed.farm.global.exception.ErrorCode;
import com.nonamed.farm.global.exception.NonamedException;

public class UserNotDiaryException extends NonamedException {

	public final static UserNotDiaryException EXCEPTION = new UserNotDiaryException();

	public UserNotDiaryException() {
		super(ErrorCode.USER_NOT_DIARY);
	}

}
