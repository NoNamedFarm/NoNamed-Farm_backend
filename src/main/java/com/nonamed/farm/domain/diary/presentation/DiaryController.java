package com.nonamed.farm.domain.diary.presentation;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nonamed.farm.domain.diary.presentation.dto.request.DiaryRequest;
import com.nonamed.farm.domain.diary.service.DiaryService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/diary")
@RequiredArgsConstructor
@RestController
public class DiaryController {
	
	private final DiaryService diaryService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long saveDiary(@RequestBody @Valid DiaryRequest request) {
		return diaryService.saveDiary(request);
	}

	@PutMapping("/{id}")
	public Long updateDiary(@PathVariable("id") @NotBlank Long diaryId,
						@RequestBody @Valid DiaryRequest request) {
		return diaryService.updateDiary(diaryId, request);
	}

}