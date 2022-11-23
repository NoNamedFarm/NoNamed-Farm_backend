package com.nonamed.farm.domain.fram.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

	public CycleListResponse getDate(Long farmId) {
		LocalDate end = LocalDate.now();
		LocalDate start = LocalDate.of(end.getYear(), end.getMonth(), 1);

		List<CycleListResponse.CycleResponse> lightCycle = cycleRepository.findByIsLightAndDateBetween(true, start, end)
			.stream().map(this::ofCycleResponse).collect(Collectors.toList());
		List<CycleListResponse.CycleResponse>  waterCycle = cycleRepository.findByIsWaterAndDateBetween(true, start, end)
			.stream().map(this::ofCycleResponse).collect(Collectors.toList());

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

	private CycleListResponse.CycleResponse ofCycleResponse(Cycle cycle) {
		return new CycleListResponse.CycleResponse(cycle.getDate());
	}


}
