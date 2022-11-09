package com.nonamed.farm.domain.user.service.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.nonamed.farm.domain.user.domain.User;
import com.nonamed.farm.domain.user.domain.repository.UserRepository;
import com.nonamed.farm.domain.user.exception.UserNotFoundException;
import com.nonamed.farm.global.jwt.exception.TokenUnauthorizedException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthUtil {

	private final UserRepository userRepository;

	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			throw TokenUnauthorizedException.EXCEPTION;
		}
		return authentication.getName();
	}

	public User getUser() {
		return userRepository.findById(getUserId())
			.orElseThrow(() -> UserNotFoundException.EXCEPTION);
	}

}
