package com.nonamed.farm.domain.diary.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.nonamed.farm.global.entity.BaseIdEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Diary extends BaseIdEntity {

	@Column(nullable = false, length = 1000)
	private String content;

	@Column(nullable = false)
	private String userId;

	private LocalDate date;

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

}
