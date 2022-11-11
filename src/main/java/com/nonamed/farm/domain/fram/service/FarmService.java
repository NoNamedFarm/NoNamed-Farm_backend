package com.nonamed.farm.domain.fram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nonamed.farm.domain.fram.domain.Farm;
import com.nonamed.farm.domain.fram.domain.repository.FarmRepository;
import com.nonamed.farm.domain.fram.exception.DeviceNotFoundException;
import com.nonamed.farm.domain.fram.presentation.dto.request.FarmRequest;

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
		return farmRepository.save(farm).getId();
	}

	


}
