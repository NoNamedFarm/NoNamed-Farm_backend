package com.nonamed.farm.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class UserDetailResponse {

	private final String nickname;

	private final long createDate; // 계정을 생성한지 얼마나 지났는지

	private final Long totalFarm;
	private final Long totalDiary;

}
