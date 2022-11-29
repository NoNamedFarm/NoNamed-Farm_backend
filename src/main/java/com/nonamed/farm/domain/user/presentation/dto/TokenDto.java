package com.nonamed.farm.domain.user.presentation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenDto {

    private String accessToken;

    private String refreshToken;

    @Builder
    private TokenDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
