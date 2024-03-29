package com.nonamed.farm.domain.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.nonamed.farm.global.entity.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {

	@Id
	@Column(nullable = false, length = 10)
	private String userId;

	@Column(nullable = false, length = 4)
	private String nickname;

	@Column(nullable = false, length = 60)
	private String password;

	@Builder
	private User(String nickname, String userId, String password) {
		this.nickname = nickname;
		this.userId = userId;
		this.password = password;
	}

}
