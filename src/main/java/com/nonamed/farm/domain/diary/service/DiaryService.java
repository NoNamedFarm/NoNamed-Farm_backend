package com.nonamed.farm.domain.diary.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nonamed.farm.domain.diary.domain.Diary;
import com.nonamed.farm.domain.diary.domain.repository.DiaryRepository;
import com.nonamed.farm.domain.diary.exception.DiaryNotFoundException;
import com.nonamed.farm.domain.diary.exception.NotRightDateException;
import com.nonamed.farm.domain.diary.presentation.dto.request.DiaryRequest;
import com.nonamed.farm.domain.diary.presentation.dto.response.DiaryIdResponse;
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
	public DiaryIdResponse saveDiary(DiaryRequest request) {
		if(!LocalDate.now().isAfter(request.getDate())&&!LocalDate.now().isEqual(request.getDate())) {
			throw NotRightDateException.EXCEPTION;
		}

		return new DiaryIdResponse(diaryRepository.save(Diary.builder()
				.date(request.getDate())
				.content(request.getContent())
				.userId(authUtil.getUserId())
			.build()).getId());

	}

	@Transactional
	public DiaryIdResponse updateDiary(Long diaryId, DiaryRequest request) {
		if(!LocalDate.now().isAfter(request.getDate())&&!LocalDate.now().isEqual(request.getDate())) {
			throw NotRightDateException.EXCEPTION;
		}

		Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> DiaryNotFoundException.EXCEPTION);
		diary.compare(diary.getUserId());

		return new DiaryIdResponse(diaryRepository.save(diary.update(request.getContent(), request.getDate())).getId());
	}

	@Transactional
	public void deleteDiary(Long diaryId) {
		Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> DiaryNotFoundException.EXCEPTION);
		diary.compare(diary.getUserId());

		diaryRepository.delete(diary);
	}

	public DiaryListResponse getDiaryList(Pageable page) {
		Page<Diary> diaries = diaryRepository.findAllByUserIdOrderByDateDesc(authUtil.getUserId(), page);
		return new DiaryListResponse(diaries.getTotalPages(), diaries.map(this::ofDiaryResponse).toList());
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
