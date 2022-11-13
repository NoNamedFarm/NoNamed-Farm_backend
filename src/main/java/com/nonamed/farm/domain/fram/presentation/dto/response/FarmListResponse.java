package com.nonamed.farm.domain.fram.presentation.dto.response;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FarmListResponse {

	private final int totalPage;
	private final List<FarmResponse> farmResponses;

	@Getter @Builder
	public static class FarmResponse {

		private final Long id;

		private final String farmName;
		private final String farmCrop;
		private final LocalDate createdDate;

		private final float temperature;
		private final float airHumidity;
		private final int soilHumidity;

	}
}
