package com.nonamed.farm.domain.fram.service;

import org.springframework.stereotype.Service;

import com.nonamed.farm.domain.fram.domain.Farm;
import com.nonamed.farm.domain.fram.domain.repository.FarmRepository;
import com.nonamed.farm.domain.fram.exception.FarmNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FarmDetailService {

	private final FarmRepository farmRepository;
	
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
