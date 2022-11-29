package com.nonamed.farm.domain.diary.presentation.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class DiaryRequest {

	private LocalDate date;

	@NotBlank(message = "일지의 내용을 작성해주세요.")
	@Size(min = 2, max = 1000, message = "2자 이상, 1000자 이하로 작성해주세요.")
	private String content;

}
