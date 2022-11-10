package com.nonamed.farm.domain.fram.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.nonamed.farm.domain.fram.domain.Cycle;
import com.nonamed.farm.domain.fram.domain.Farm;
import com.nonamed.farm.domain.fram.domain.repository.CycleRepository;
import com.nonamed.farm.domain.fram.domain.repository.FarmRepository;
import com.nonamed.farm.domain.fram.exception.FarmNotFoundException;
import com.nonamed.farm.domain.fram.presentation.dto.response.FarmResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FarmDetailService {

	private final FarmRepository farmRepository;
	private final CycleRepository cycleRepository;

	public FarmResponse getFarmDetail(Long farmId) {
		Farm farm = farmRepository.findById(farmId)
			.orElseThrow(() -> FarmNotFoundException.EXCEPTION);
		LocalDate date = cycleRepository.findFirstByFarmIdOrderByDateDesc(farmId)
			.orElseThrow(null).getDate();

		return FarmResponse.builder()
			.id(farm.getId())
			.farmName(farm.getFarmName())
			.farmCrop(farm.getFarmCrop())
			.createdDate(farm.getCreatedDate())
			.airHumidity(farm.getAirHumidity())
			.soilHumidity(farm.getSoilHumidity())
			.temperature(farm.getTemperature())
			.isLight(farm.getIsLight())
			.isWater(farm.getIsWater())
			.cycleDate(date)
			.build();

	}

	public void isWater(Long farmId) {
		Farm farm = farmRepository.findById(farmId)
			.orElseThrow(() -> FarmNotFoundException.EXCEPTION);
		farm.compare(farm.getUserId());

		farm.isWater();
		farmRepository.save(farm);
	}

	public void isLight(Long farmId) {
		Farm farm = farmRepository.findById(farmId)
			.orElseThrow(() -> FarmNotFoundException.EXCEPTION);
		farm.compare(farm.getUserId());

		farm.isLight();
		farmRepository.save(farm);
	}


}
