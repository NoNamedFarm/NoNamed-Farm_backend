package com.nonamed.farm.domain.fram.presentation.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class FarmUpdateRequest {

	@NotBlank
	@Size(max = 10)
	private String farmName;

	@NotBlank
	@Size(max = 15)
	private String farmCrop;

}
