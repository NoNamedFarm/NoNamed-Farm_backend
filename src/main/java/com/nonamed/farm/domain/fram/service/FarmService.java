package com.nonamed.farm.domain.fram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nonamed.farm.domain.fram.domain.Farm;
import com.nonamed.farm.domain.fram.domain.repository.FarmRepository;
import com.nonamed.farm.domain.fram.exception.DeviceNotFoundException;
import com.nonamed.farm.domain.fram.exception.FarmNotFoundException;
import com.nonamed.farm.domain.fram.presentation.dto.request.FarmRequest;
import com.nonamed.farm.domain.fram.presentation.dto.request.FarmUpdateRequest;
import com.nonamed.farm.domain.user.service.util.AuthUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FarmService {

	private final FarmRepository farmRepository;
	private final AuthUtil authUtil;

	@Transactional
	public Long saveFarm(FarmRequest request) {
		Farm farm = farmRepository.findByDeviceId(request.getDeviceId())
				.orElseThrow(() -> DeviceNotFoundException.EXCEPTION);

		farm.createFarm(request.getFarmName(), request.getFarmCrop(), authUtil.getUserId());
		return farmRepository.save(farm).getId();
	}

	@Transactional
	public Long updateFarm(Long farmId, FarmUpdateRequest request) {
		Farm farm = farmRepository.findById(farmId)
			.orElseThrow(() -> FarmNotFoundException.EXCEPTION);
		farm.compare(authUtil.getUserId());

		farm.updateFarm(request.getFarmName(), request.getFarmCrop());
		return farmRepository.save(farm).getId();
	}

	@Transactional
	public void deleteFarm(Long farmId) {
		Farm farm = farmRepository.findById(farmId)
			.orElseThrow(() -> FarmNotFoundException.EXCEPTION);
		farm.compare(authUtil.getUserId());

		farm.deleteFarm();
	}

}
