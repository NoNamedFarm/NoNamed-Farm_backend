package com.nonamed.farm.domain.fram.domain.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.nonamed.farm.domain.fram.domain.Cycle;

public interface CycleRepository extends CrudRepository<Cycle, Long> {
	Optional<Cycle> findFirstByFarmIdOrderByDateDesc(Long farmId);
	List<Cycle> findByIsLightAndDateBetween(Boolean isLight, LocalDate start, LocalDate end);
	List<Cycle> findByIsWaterAndDateBetween(Boolean isWater, LocalDate start, LocalDate end);
}
