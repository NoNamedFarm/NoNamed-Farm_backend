package com.nonamed.farm.domain.fram.presentation.dto.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class FarmDetailResponse {

	private final Long id;

	private final String farmName;
	private final String farmCrop;
	private final LocalDate createdDate;

	private final float temperature;
	private final float airHumidity;
	private final int soilHumidity;

	private final Boolean isWater;
	private final Boolean isLight;

	private final LocalDate lastCycleDate;

}
