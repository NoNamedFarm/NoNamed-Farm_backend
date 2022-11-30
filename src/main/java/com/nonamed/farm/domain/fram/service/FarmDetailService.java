package com.nonamed.farm.domain.fram.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nonamed.farm.domain.fram.domain.Cycle;
import com.nonamed.farm.domain.fram.domain.Farm;
import com.nonamed.farm.domain.fram.domain.repository.CycleRepository;
import com.nonamed.farm.domain.fram.domain.repository.FarmRepository;
import com.nonamed.farm.domain.fram.exception.CycleNotFoundException;
import com.nonamed.farm.domain.fram.exception.FarmNotFoundException;
import com.nonamed.farm.domain.fram.presentation.dto.response.CycleListResponse;
import com.nonamed.farm.domain.fram.presentation.dto.response.FarmDetailResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FarmDetailService {

	private final FarmRepository farmRepository;
	private final CycleRepository cycleRepository;

	public FarmDetailResponse getFarmDetail(Long farmId) {
		Farm farm = farmRepository.findById(farmId)
			.orElseThrow(() -> FarmNotFoundException.EXCEPTION);
		LocalDate date = cycleRepository.findFirstByFarmIdOrderByDateDesc(farmId)
			.orElse(Cycle.builder().farmId(0L).build()).getDate();

		return FarmDetailResponse.builder()
			.id(farm.getId())
			.farmName(farm.getFarmName())
			.farmCrop(farm.getFarmCrop())
			.createdDate(farm.getCreatedDate())
			.airHumidity(farm.getAirHumidity())
			.soilHumidity(farm.getSoilHumidity())
			.temperature(farm.getTemperature())
			.isLight(farm.getIsLight())
			.isWater(farm.getIsWater())
			.lastCycleDate(ChronoUnit.DAYS.between(date, LocalDate.now()))
			.build();

	}

	public CycleListResponse getDate(Long farmId, int year, int month) {
		LocalDate start = LocalDate.of(year, month,1);
		LocalDate end = start.with(TemporalAdjusters.lastDayOfMonth());

		List<LocalDate> lightCycle = cycleRepository.findByFarmIdAndIsLightAndDateBetween(farmId, true, start, end)
			.stream().map(cycle -> { return cycle.getDate();}).collect(Collectors.toList());
		List<LocalDate>  waterCycle = cycleRepository.findByFarmIdAndIsWaterAndDateBetween(farmId, true, start, end)
			.stream().map(cycle -> { return cycle.getDate();}).collect(Collectors.toList());

		return new CycleListResponse(lightCycle, waterCycle);
	}

	public void isWater(Long farmId) {
		Farm farm = farmRepository.findById(farmId)
			.orElseThrow(() -> FarmNotFoundException.EXCEPTION);
		farm.compare(farm.getUserId());

		farm.isWater();

		if(farm.getIsWater().equals("1")) saveWaterCycle(farm.getId());

		farmRepository.save(farm);
	}

	public void isLight(Long farmId) {
		Farm farm = farmRepository.findById(farmId)
			.orElseThrow(() -> FarmNotFoundException.EXCEPTION);
		farm.compare(farm.getUserId());

		farm.isLight();

		if(farm.getIsLight().equals("1")) saveLightCycle(farm.getId());

		farmRepository.save(farm);
	}

	private void saveLightCycle(Long farmId) {
		if(cycleRepository.existsByDateAndFarmId(LocalDate.now(), farmId)) {
			Cycle cycle = cycleRepository.findByDateAndFarmId(LocalDate.now(), farmId)
				.orElseThrow(() -> CycleNotFoundException.EXCEPTION);
			cycle.isState(cycle.getIsWater(), true);
		} else {
			cycleRepository.save(Cycle.builder()
				.isLight(true)
				.isWater(false)
				.farmId(farmId)
				.build());
		}
	}

	private void saveWaterCycle(Long farmId) {
		if(cycleRepository.existsByDateAndFarmId(LocalDate.now(), farmId)) {
			Cycle cycle = cycleRepository.findByDateAndFarmId(LocalDate.now(), farmId)
				.orElseThrow(() -> CycleNotFoundException.EXCEPTION);
			cycle.isState(true, cycle.getIsLight());
		} else {
			cycleRepository.save(Cycle.builder()
				.isLight(false)
				.isWater(true)
				.farmId(farmId)
				.build());
		}
	}

}
