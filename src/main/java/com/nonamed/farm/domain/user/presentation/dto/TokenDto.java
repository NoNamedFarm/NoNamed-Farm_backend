package com.nonamed.farm.domain.user.presentation.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenDto {

    private String accessToken;

    private String refreshToken;

    private LocalDateTime expiryTime;

    @Builder
    private TokenDto(String accessToken, String refreshToken, LocalDateTime expiryTime) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiryTime = expiryTime;
    }

}
