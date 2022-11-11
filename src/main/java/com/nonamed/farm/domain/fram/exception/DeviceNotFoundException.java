package com.nonamed.farm.domain.fram.exception;

import com.nonamed.farm.global.exception.ErrorCode;
import com.nonamed.farm.global.exception.NonamedException;

public class DeviceNotFoundException extends NonamedException {

	public final static DeviceNotFoundException EXCEPTION = new DeviceNotFoundException();

	public DeviceNotFoundException() {
		super(ErrorCode.DEVICE_NOT_FOUND);
	}

}
