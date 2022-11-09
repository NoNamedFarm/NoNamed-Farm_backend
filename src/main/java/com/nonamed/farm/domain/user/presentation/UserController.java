package com.nonamed.farm.domain.user.presentation;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nonamed.farm.domain.user.presentation.dto.TokenDto;
import com.nonamed.farm.domain.user.presentation.dto.request.LoginRequest;
import com.nonamed.farm.domain.user.presentation.dto.request.SignUpRequest;
import com.nonamed.farm.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.CREATED)
	public TokenDto login(@RequestBody @Valid LoginRequest request) {
		return userService.login(request);
	}

	@PostMapping("/sign-up")
	@ResponseStatus(HttpStatus.CREATED)
	public TokenDto signUp(@RequestBody @Valid SignUpRequest request) {
		return userService.signUp(request);
	}

	@GetMapping("/check")
	public void overlapCheckUserId(@NotBlank @RequestParam("user-id")String userId) {
		userService.overlapCheckUserId(userId);
	}

}
