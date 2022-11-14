package com.nonamed.farm.domain.user.presentation.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class TokenRequest {

    @NotBlank
    private String accessToken;

    @NotBlank
    private String refreshToken;

}
