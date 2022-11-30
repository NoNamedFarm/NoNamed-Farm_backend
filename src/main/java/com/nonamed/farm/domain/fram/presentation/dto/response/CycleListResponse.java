package com.nonamed.farm.domain.fram.presentation.dto.response;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CycleListResponse {

	private final List<LocalDate> waterCycleResponses;
	private final List<LocalDate> lightCycleResponses;

}
