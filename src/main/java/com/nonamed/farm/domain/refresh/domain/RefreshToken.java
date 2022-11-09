package com.nonamed.farm.domain.refresh.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RefreshToken {

    @Id
    private String id;

    @Column(nullable = false)
    private String refreshToken;

    @Builder
    public RefreshToken(String id, String refreshToken) {
        this.id = id;
        this.refreshToken = refreshToken;
    }

    public RefreshToken update(String refreshTokenValue) {
        this.refreshToken = refreshTokenValue;
        return this;
    }
}