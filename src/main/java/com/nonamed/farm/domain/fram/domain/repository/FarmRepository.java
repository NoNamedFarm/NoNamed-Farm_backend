package com.nonamed.farm.domain.fram.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.nonamed.farm.domain.fram.domain.Farm;

public interface FarmRepository extends CrudRepository<Farm, Long> {
	Optional<Farm> findByDeviceId(String deviceId);
	Page<Farm> findByUserIdOrderById(String userId, Pageable page);
}
