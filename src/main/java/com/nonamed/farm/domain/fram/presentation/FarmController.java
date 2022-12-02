package com.nonamed.farm.domain.fram.presentation;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nonamed.farm.domain.fram.presentation.dto.request.FarmRequest;
import com.nonamed.farm.domain.fram.presentation.dto.request.FarmUpdateRequest;
import com.nonamed.farm.domain.fram.presentation.dto.response.CycleListResponse;
import com.nonamed.farm.domain.fram.presentation.dto.response.FarmDetailResponse;
import com.nonamed.farm.domain.fram.presentation.dto.response.FarmIdResponse;
import com.nonamed.farm.domain.fram.presentation.dto.response.FarmListResponse;
import com.nonamed.farm.domain.fram.service.FarmDetailService;
import com.nonamed.farm.domain.fram.service.FarmService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/farm")
@RestController
@RequiredArgsConstructor
public class FarmController {

	private final FarmService farmService;
	private final FarmDetailService farmDetailService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FarmIdResponse saveFarm(@RequestBody @Valid FarmRequest request) {
		return farmService.saveFarm(request);
	}

	@PutMapping("/{id}")
	public FarmIdResponse updateFarm(@PathVariable("id") @NotBlank Long farmId,
							@RequestBody @Valid FarmUpdateRequest request) {
		return farmService.updateFarm(farmId, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFarm(@PathVariable("id") @NotBlank Long farmId) {
		farmService.deleteFarm(farmId);
	}

	@GetMapping("/{id}")
	public FarmDetailResponse getFarmDetail(@PathVariable("id") @NotBlank Long farmId) {
		return farmDetailService.getFarmDetail(farmId);
	}

	@GetMapping
	public FarmListResponse getFarmList(@PageableDefault Pageable page) {
		return farmService.getFarmList(page);
	}

	@GetMapping("/cycle/{id}")
	public CycleListResponse getFarmCycle(@PathVariable("id") @NotBlank Long farmId,
											@RequestParam("year")int year, @RequestParam("month")int month) {
		return farmDetailService.getDate(farmId, year, month);
	}

	@PutMapping("/switch/water/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void switchWater(@PathVariable("id") @NotBlank Long farmId) {
		farmDetailService.isWater(farmId);
	}

	@PutMapping("/switch/light/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void switchLight(@PathVariable("id") @NotBlank Long farmId) {
		farmDetailService.isLight(farmId);
	}

}
