package com.nonamed.farm.domain.fram.presentation.dto.response;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CycleListResponse {

	private final List<CycleResponse> waterCycleResponses;
	private final List<CycleResponse> lightCycleResponses;

	@Getter
	@AllArgsConstructor
	public static class CycleResponse {
		private final LocalDate date;
	}

}
