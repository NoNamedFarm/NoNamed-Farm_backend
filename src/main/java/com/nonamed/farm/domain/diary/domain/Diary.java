package com.nonamed.farm.domain.diary.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.nonamed.farm.global.entity.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Diary extends BaseTimeEntity {

	@Column(nullable = false, length = 1000)
	private String content;

	@Column(nullable = false)
	private Long userId;

	@Builder
	private Diary(String content, Long userId) {
		this.content = content;
		this.userId = userId;
	}

}
