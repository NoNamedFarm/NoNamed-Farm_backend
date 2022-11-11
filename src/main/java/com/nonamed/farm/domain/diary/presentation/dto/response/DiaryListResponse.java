package com.nonamed.farm.domain.diary.presentation.dto.response;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DiaryListResponse {

	private final List<DiaryResponse> diaryResponses;

	@Getter @Builder
	public static class DiaryResponse {

		private final Long id;
		private final LocalDate date;

	}

}
