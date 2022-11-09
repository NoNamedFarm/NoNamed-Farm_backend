package com.nonamed.farm.domain.user.exception;

import com.nonamed.farm.global.exception.ErrorCode;
import com.nonamed.farm.global.exception.NonamedException;

public class UserIdOverlapExistException extends NonamedException {

	public final static UserIdOverlapExistException EXCEPTION = new UserIdOverlapExistException();

	public UserIdOverlapExistException() {
		super(ErrorCode.USER_ID_OVERLAP_EXIST);
	}

}
