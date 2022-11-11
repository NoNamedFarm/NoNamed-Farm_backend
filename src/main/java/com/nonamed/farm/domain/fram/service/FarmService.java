package com.nonamed.farm.domain.fram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nonamed.farm.domain.fram.domain.Farm;
import com.nonamed.farm.domain.fram.domain.repository.FarmRepository;
import com.nonamed.farm.domain.fram.exception.DeviceNotFoundException;
import com.nonamed.farm.domain.fram.exception.FarmNotFoundException;
import com.nonamed.farm.domain.fram.presentation.dto.request.FarmRequest;
import com.nonamed.farm.domain.fram.presentation.dto.request.FarmUpdateRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FarmService {

	private final FarmRepository farmRepository;

	@Transactional
	public Long saveFarm(FarmRequest request) {
		Farm farm = farmRepository.findByDeviceId(request.getDeviceId())
				.orElseThrow(() -> DeviceNotFoundException.EXCEPTION);

		farm.updateFarm(request.getFarmName(), request.getFarmCrop());
		farm.updateDate();
		return farmRepository.save(farm).getId();
	}

	@Transactional
	public Long updateFarm(Long farmId, FarmUpdateRequest request) {
		Farm farm = farmRepository.findById(farmId)
			.orElseThrow(() -> FarmNotFoundException.EXCEPTION);

		farm.updateFarm(request.getFarmName(), request.getFarmCrop());
		return farmRepository.save(farm).getId();
	}


}
