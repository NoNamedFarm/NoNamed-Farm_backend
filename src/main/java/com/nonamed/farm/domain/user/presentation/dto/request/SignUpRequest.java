package com.nonamed.farm.domain.user.presentation.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class SignUpRequest {

	@NotBlank(message = "닉네임을 작성해주세요.")
	@Size(min = 2, max = 4, message = "2자 이상, 4자 이하로 작성해주세요.")
	private String nickname;

	@NotBlank(message = "아이디를 작성해주세요.")
	@Size(min = 2, max = 10, message = "2자 이상, 10자 이하로 작성해주세요.")
	private String userId;

	@NotBlank
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,24}$",
		message = "password는 소문자, 숫자, 특수문자가 포함되어야 합니다.")
	private String password;

}
