package com.nonamed.farm.domain.diary.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nonamed.farm.domain.diary.domain.Diary;
import com.nonamed.farm.domain.diary.domain.repository.DiaryRepository;
import com.nonamed.farm.domain.diary.exception.DiaryNotFoundException;
import com.nonamed.farm.domain.diary.presentation.dto.request.DiaryRequest;
import com.nonamed.farm.domain.diary.presentation.dto.response.DiaryListResponse;
import com.nonamed.farm.domain.diary.presentation.dto.response.DiaryResponse;
import com.nonamed.farm.domain.user.service.util.AuthUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiaryService {

	private final DiaryRepository diaryRepository;
	private final AuthUtil authUtil;

	@Transactional
	public Long saveDiary(DiaryRequest request) {

		return diaryRepository.save(Diary.builder()
				.date(request.getDate())
				.userId(request.getContent())
				.content(authUtil.getUserId())
			.build()).getId();

	}

	@Transactional
	public Long updateDiary(Long diaryId, DiaryRequest request) {
		Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> DiaryNotFoundException.EXCEPTION);
		diary.compare(diary.getUserId());

		return diaryRepository.save(diary.update(request.getContent(), request.getDate())).getId();
	}

	@Transactional
	public void deleteDiary(Long diaryId) {
		Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> DiaryNotFoundException.EXCEPTION);
		diary.compare(diary.getUserId());

		diaryRepository.delete(diary);
	}

	public DiaryListResponse getDiaryList(Pageable page) {
		return new DiaryListResponse(diaryRepository.findAllByUserIdOrderByDateDesc(authUtil.getUserId(), page)
			.map(this::ofDiaryResponse).toList());
	}

	public DiaryResponse getDiary(Long diaryId) {
		Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> DiaryNotFoundException.EXCEPTION);
		diary.compare(diary.getUserId());

		return DiaryResponse.builder()
			.id(diary.getId())
			.date(diary.getDate())
			.content(diary.getContent())
			.build();
	}

	private DiaryListResponse.DiaryResponse ofDiaryResponse(Diary diary) {
		return DiaryListResponse.DiaryResponse.builder()
			.id(diary.getId())
			.date(diary.getDate())
			.build();
	}

}
