package com.nonamed.farm.domain.user.presentation;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nonamed.farm.domain.user.presentation.dto.TokenDto;
import com.nonamed.farm.domain.user.presentation.dto.request.LoginRequest;
import com.nonamed.farm.domain.user.presentation.dto.request.SignUpRequest;
import com.nonamed.farm.domain.user.presentation.dto.response.UserDetailResponse;
import com.nonamed.farm.domain.user.service.AuthService;
import com.nonamed.farm.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

	private final AuthService authService;
	private final UserService userService;

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.CREATED)
	public TokenDto login(@RequestBody @Valid LoginRequest request) {
		return authService.login(request);
	}

	@PostMapping("/sign-up")
	@ResponseStatus(HttpStatus.CREATED)
	public TokenDto signUp(@RequestBody @Valid SignUpRequest request) {
		return authService.signUp(request);
	}

	@PutMapping("/refresh")
	public TokenDto reassignToken(@RequestHeader("Refresh-Token")String token) {
		return authService.reassignToken(token);
	}

	@GetMapping("/check")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void overlapCheckUserId(@NotBlank @RequestParam("user")String userId) {
		authService.overlapCheckUserId(userId);
	}

	@DeleteMapping("/logout")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void logout() {
		authService.logout();
	}

	@GetMapping
	public UserDetailResponse getUser() {
		return userService.getUser();
	}

}
