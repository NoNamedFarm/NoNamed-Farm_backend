package com.nonamed.farm.domain.user.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.nonamed.farm.domain.diary.domain.repository.DiaryRepository;
import com.nonamed.farm.domain.fram.domain.repository.FarmRepository;
import com.nonamed.farm.domain.user.domain.User;
import com.nonamed.farm.domain.user.presentation.dto.response.UserDetailResponse;
import com.nonamed.farm.domain.user.service.util.AuthUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final DiaryRepository diaryRepository;
	private final FarmRepository farmRepository;

	private final AuthUtil authUtil;

	public UserDetailResponse getUser() {
		User user = authUtil.getUser();

		return UserDetailResponse.builder()
			.nickname(user.getNickname())
			.totalDiary(diaryRepository.countByUserId(user.getUserId()))
			.totalFarm(farmRepository.countByUserId(user.getUserId()))
			.createDate(ChronoUnit.DAYS.between(user.getCreatedDate(), LocalDate.now()))
			.build();
	}

}
