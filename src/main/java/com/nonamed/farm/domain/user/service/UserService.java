package com.nonamed.farm.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nonamed.farm.domain.refresh.domain.RefreshToken;
import com.nonamed.farm.domain.refresh.domain.repository.RefreshTokenRepository;
import com.nonamed.farm.domain.user.domain.User;
import com.nonamed.farm.domain.user.domain.repository.UserRepository;
import com.nonamed.farm.domain.user.exception.NotMatchesPasswordException;
import com.nonamed.farm.domain.user.exception.RefreshTokenNotFoundException;
import com.nonamed.farm.domain.user.exception.UserIdOverlapExistException;
import com.nonamed.farm.domain.user.exception.UserNotFoundException;
import com.nonamed.farm.domain.user.exception.ValidateTokenException;
import com.nonamed.farm.domain.user.presentation.dto.TokenDto;
import com.nonamed.farm.domain.user.presentation.dto.request.LoginRequest;
import com.nonamed.farm.domain.user.presentation.dto.request.SignUpRequest;
import com.nonamed.farm.domain.user.service.util.AuthUtil;
import com.nonamed.farm.global.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final RefreshTokenRepository refreshTokenRepository;
	private final AuthUtil authUtil;

	private final PasswordEncoder encoder;
	private final JwtTokenProvider provider;

	public TokenDto login(LoginRequest request) {
		User user = userRepository.findById(request.getUserId())
			.orElseThrow(() -> UserNotFoundException.EXCEPTION);

		if(encoder.matches(request.getPassword(), user.getPassword())) {
			throw NotMatchesPasswordException.EXCEPTION;
		}

		return TokenDto.builder()
			.accessToken(provider.generateToken(user.getUserId()))
			.refreshToken(provider.getRefreshToken(user.getUserId()))
			.expiryTime(provider.getExpiryTime())
			.build();

	}

	public TokenDto signUp(SignUpRequest request) {
		overlapCheckUserId(request.getUserId());

		User user = userRepository.save(User.builder()
				.userId(request.getUserId())
				.password(encoder.encode(request.getPassword()))
				.nickname(request.getNickname())
			.build());

		return TokenDto.builder()
			.accessToken(provider.generateToken(user.getUserId()))
			.refreshToken(provider.getRefreshToken(user.getUserId()))
			.expiryTime(provider.getExpiryTime())
			.build();

	}

	public void overlapCheckUserId(String userId) {
		if(userRepository.existsByUserId(userId)) throw UserIdOverlapExistException.EXCEPTION;
	}

	public TokenDto reassignToken(TokenDto dto) {
		if(provider.validateToken(dto.getAccessToken())) throw ValidateTokenException.EXCEPTION;
		provider.isRefreshToken(dto.getRefreshToken());

		RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(dto.getRefreshToken())
			.orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

		User user = userRepository.findById(refreshToken.getId())
			.orElseThrow(() -> UserNotFoundException.EXCEPTION);

		TokenDto token = TokenDto.builder()
			.accessToken(provider.generateToken(user.getUserId()))
			.refreshToken(provider.getRefreshToken(user.getUserId()))
			.expiryTime(provider.getExpiryTime())
			.build();

		refreshTokenRepository.save(refreshToken.update(token.getRefreshToken()));

		return token;
	}

	public void logout() {
		RefreshToken refreshToken = refreshTokenRepository.findById(authUtil.getUserId())
			.orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

		refreshTokenRepository.delete(refreshToken);
	}

}
