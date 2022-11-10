package com.nonamed.farm.domain.fram.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.nonamed.farm.domain.fram.domain.Cycle;

public interface CycleRepository extends CrudRepository<Cycle, Long> {
	Optional<Cycle> findFirstByFarmIdOrderByDateDesc(Long farmId);
}
