package com.nonamed.farm.domain.diary.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.nonamed.farm.domain.diary.domain.Diary;

public interface DiaryRepository extends CrudRepository<Diary, Long> {
	Page<Diary> findAllByUserIdOrderByDateDesc(String userId, Pageable page);
}
