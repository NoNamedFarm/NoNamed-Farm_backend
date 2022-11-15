package com.nonamed.farm.domain.diary.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.nonamed.farm.domain.diary.exception.UserNotDiaryException;
import com.nonamed.farm.global.entity.BaseIdEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Diary extends BaseIdEntity {

	private LocalDate date;

	@Column(nullable = false, length = 1000)
	private String content;

	@Column(nullable = false)
	private String userId;

	@Builder
	private Diary(String content, String userId, LocalDate date) {
		this.content = content;
		this.userId = userId;
		this.date = date;
	}

	public Diary update(String content, LocalDate date) {
		this.content = content;
		this.date = date;
		return this;
	}

	public void compare(String userId) {
		if(!this.userId.equals(userId)) throw UserNotDiaryException.EXCEPTION;
	}

}
