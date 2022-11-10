package com.nonamed.farm.domain.fram.presentation;

import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nonamed.farm.domain.fram.service.FarmDetailService;
import com.nonamed.farm.domain.fram.service.FarmService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/farm")
@RestController
@RequiredArgsConstructor
public class FarmController {

	private final FarmService farmService;
	private final FarmDetailService farmDetailService;

	@GetMapping("/switch/water/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void switchWater(@PathVariable("id") @NotBlank Long farmId) {
		farmDetailService.isWater(farmId);
	}

	@GetMapping("/switch/light/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void switchLight(@PathVariable("id") @NotBlank Long farmId) {
		farmDetailService.isLight(farmId);
	}


}
