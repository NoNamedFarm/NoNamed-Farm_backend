package com.nonamed.farm.domain.user.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.nonamed.farm.domain.user.domain.User;

public interface UserRepository extends CrudRepository<User, String> {
	Boolean existsByUserId(String UserId);
}
