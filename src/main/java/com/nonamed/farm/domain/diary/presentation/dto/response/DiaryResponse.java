package com.nonamed.farm.domain.diary.presentation.dto.response;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @Builder
public class DiaryResponse {

	private final Long id;
	private final LocalDate date;
	private final String content;

}
