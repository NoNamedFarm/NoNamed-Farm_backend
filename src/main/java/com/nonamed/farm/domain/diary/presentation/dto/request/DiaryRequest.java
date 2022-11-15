package com.nonamed.farm.domain.diary.presentation.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class DiaryRequest {

	private LocalDate date;

	@NotBlank
	@Size(min = 2, max = 1000)
	private String content;

}
