package com.nonamed.farm.domain.diary.presentation;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nonamed.farm.domain.diary.presentation.dto.request.DiaryRequest;
import com.nonamed.farm.domain.diary.presentation.dto.response.DiaryIdResponse;
import com.nonamed.farm.domain.diary.presentation.dto.response.DiaryListResponse;
import com.nonamed.farm.domain.diary.presentation.dto.response.DiaryResponse;
import com.nonamed.farm.domain.diary.service.DiaryService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/diary")
@RequiredArgsConstructor
@RestController
public class DiaryController {
	
	private final DiaryService diaryService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DiaryIdResponse saveDiary(@RequestBody @Valid DiaryRequest request) {
		return diaryService.saveDiary(request);
	}

	@PutMapping("/{id}")
	public DiaryIdResponse updateDiary(@PathVariable("id") @NotBlank Long diaryId,
						@RequestBody @Valid DiaryRequest request) {
		return diaryService.updateDiary(diaryId, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDiary(@PathVariable("id") @NotBlank Long diaryId) {
		diaryService.deleteDiary(diaryId);
	}

	@GetMapping
	public DiaryListResponse getDiaryList(@PageableDefault Pageable page) {
		return diaryService.getDiaryList(page);
	}

	@GetMapping("/{id}")
	public DiaryResponse getDiary(@PathVariable("id") @NotBlank Long diaryId) {
		return diaryService.getDiary(diaryId);
	}

}
