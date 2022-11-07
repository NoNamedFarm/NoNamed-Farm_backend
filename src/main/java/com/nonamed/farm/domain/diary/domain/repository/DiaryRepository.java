package com.nonamed.farm.domain.diary.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.nonamed.farm.domain.diary.domain.Diary;

public interface DiaryRepository extends CrudRepository<Diary, Long> {
}
