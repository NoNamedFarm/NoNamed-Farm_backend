package com.nonamed.farm.domain.fram.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

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
	private String name;
	@Column(length = 15)
	private String crop;

	private float temperature; // 온도
	private float atmHumidity; // 공기 습도
	private int soilHumidity; // 온도 습도

	private Boolean isWater; // 물 스위치의 상태 (ON,OFF)
	private Boolean isLight; // 빛 스위치의 상태

	private LocalDate createdDate;

	private Long userId;

	public void updateDate() {
		this.createdDate = LocalDate.now();
	}

	public void updateFarm(String name, String crop) {
		this.name = name;
		this.crop = crop;
	}

	public void isState(Boolean isWater, Boolean isLight) {
		this.isWater = isWater;
		this.isLight = isLight;
	}

}
