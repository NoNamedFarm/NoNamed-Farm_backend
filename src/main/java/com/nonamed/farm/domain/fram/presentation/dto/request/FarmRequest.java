package com.nonamed.farm.domain.fram.presentation.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class FarmRequest {

	@NotBlank
	private String deviceId;

	@NotBlank(message = "농장 이름을 작성해주세요.")
	@Size(max = 10, message = "10자 이하로 작성해주세요.")
	private String farmName;

	@NotBlank(message = "기르는 작물을 작성해주세요.")
	@Size(max = 15, message = "15자 이하로 작성해주세요.")
	private String farmCrop;

}
