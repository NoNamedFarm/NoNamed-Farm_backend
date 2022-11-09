package com.nonamed.farm.domain.refresh.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.nonamed.farm.domain.refresh.domain.RefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
	Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
