package com.nonamed.farm.domain.fram.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.nonamed.farm.domain.fram.exception.UserNotFarmException;
import com.nonamed.farm.global.entity.BaseIdEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Farm extends BaseIdEntity {

	@Column(nullable = false)
	private String deviceId;

	@Column(length = 10)
	private String farmName;
	@Column(length = 15)
	private String farmCrop;

	private float temperature; // 온도
	private float airHumidity; // 공기 습도
	private int soilHumidity; // 온도 습도

	private Boolean isWater; // 물 스위치의 상태 (ON,OFF)
	private Boolean isLight; // 빛 스위치의 상태

	private LocalDate createdDate;

	private String userId;

	public void createFarm(String farmName, String farmCrop, String userId) {
		this.farmName = farmName;
		this.farmCrop = farmCrop;
		this.userId = userId;
		this.createdDate = LocalDate.now();
	}

	public void updateFarm(String farmName, String farmCrop) {
		this.farmName = farmName;
		this.farmCrop = farmCrop;
	}

	public void deleteFarm() {
		this.farmName = null;
		this.farmCrop = null;
		this.userId = null;
	}

	public void isWater() {
		this.isWater = !this.isWater;
	}

	public void isLight() {
		this.isLight = !this.isLight;
	}

	public void compare(String userId) {
		if(!this.userId.equals(userId)) throw UserNotFarmException.EXCEPTION;
	}

}
