package com.nonamed.farm.domain.user.presentation.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class LoginRequest {

	@NotBlank
	@Size(min = 2, max = 10)
	private String userId;

	@NotBlank
	@Size(min = 6, max = 20)
	private String password;

}
