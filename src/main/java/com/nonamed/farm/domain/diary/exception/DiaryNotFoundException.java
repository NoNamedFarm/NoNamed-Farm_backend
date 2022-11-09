package com.nonamed.farm.domain.diary.exception;

import com.nonamed.farm.global.exception.ErrorCode;
import com.nonamed.farm.global.exception.NonamedException;

public class DiaryNotFoundException extends NonamedException {

	public final static DiaryNotFoundException EXCEPTION = new DiaryNotFoundException();

	public DiaryNotFoundException() {
		super(ErrorCode.DIARY_NOT_FOUND);
	}

}
