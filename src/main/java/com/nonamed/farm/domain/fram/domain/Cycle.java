package com.nonamed.farm.domain.fram.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Cycle {

	@Id
	private LocalDate date;

	private Boolean isWater; // id에 해당하는 날짜에 물을 주었는지
	private Boolean isLight;

	@Column(nullable = false)
	private Long farmId;

	@Builder
	private Cycle(Long farmId, Boolean isWater, Boolean isLight) {
		this.date = LocalDate.now();
		this.isWater = isWater;
		this.isLight = isLight;
		this.farmId = farmId;
	}

	public void isState(Boolean isWater, Boolean isLight) {
		this.isWater = isWater;
		this.isLight = isLight;
	}

}
