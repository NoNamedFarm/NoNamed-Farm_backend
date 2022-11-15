package com.nonamed.farm.domain.fram.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nonamed.farm.domain.fram.domain.Farm;
import com.nonamed.farm.domain.fram.domain.repository.FarmRepository;
import com.nonamed.farm.domain.fram.exception.DeviceNotFoundException;
import com.nonamed.farm.domain.fram.exception.FarmNotFoundException;
import com.nonamed.farm.domain.fram.exception.FarmUserExistException;
import com.nonamed.farm.domain.fram.presentation.dto.request.FarmRequest;
import com.nonamed.farm.domain.fram.presentation.dto.request.FarmUpdateRequest;
import com.nonamed.farm.domain.fram.presentation.dto.response.FarmIdResponse;
import com.nonamed.farm.domain.fram.presentation.dto.response.FarmListResponse;
import com.nonamed.farm.domain.user.service.util.AuthUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FarmService {

	private final FarmRepository farmRepository;
	private final AuthUtil authUtil;

	@Transactional
	public FarmIdResponse saveFarm(FarmRequest request) {
		Farm farm = farmRepository.findByDeviceId(request.getDeviceId())
				.orElseThrow(() -> DeviceNotFoundException.EXCEPTION);

		if(!farm.getUserId().isEmpty()) {
			throw FarmUserExistException.EXCEPTION;
		}

		farm.createFarm(request.getFarmName(), request.getFarmCrop(), authUtil.getUserId());
		return new FarmIdResponse(farmRepository.save(farm).getId());
	}

	@Transactional
	public FarmIdResponse updateFarm(Long farmId, FarmUpdateRequest request) {
		Farm farm = farmRepository.findById(farmId)
			.orElseThrow(() -> FarmNotFoundException.EXCEPTION);
		farm.compare(authUtil.getUserId());

		farm.updateFarm(request.getFarmName(), request.getFarmCrop());
		return new FarmIdResponse(farmRepository.save(farm).getId());
	}

	@Transactional
	public void deleteFarm(Long farmId) {
		Farm farm = farmRepository.findById(farmId)
			.orElseThrow(() -> FarmNotFoundException.EXCEPTION);
		farm.compare(authUtil.getUserId());

		farm.deleteFarm();
	}

	public FarmListResponse getFarmList(Pageable page) {
		Page<Farm> farms = farmRepository.findByUserIdOrderById(authUtil.getUserId(), page);

		return new FarmListResponse(farms.getTotalPages(),
			farms.map(farm -> {
				return FarmListResponse.FarmResponse.builder()
					.id(farm.getId())
					.farmName(farm.getFarmName())
					.farmCrop(farm.getFarmCrop())
					.createdDate(farm.getCreatedDate())
					.airHumidity(farm.getAirHumidity())
					.soilHumidity(farm.getSoilHumidity())
					.temperature(farm.getTemperature())
					.build();
			}).toList());
	}

}
