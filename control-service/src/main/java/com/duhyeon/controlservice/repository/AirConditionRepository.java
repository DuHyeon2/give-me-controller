package com.duhyeon.controlservice.repository;

import com.duhyeon.controlservice.domain.AirCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirConditionRepository extends JpaRepository<AirCondition, Long> {
}
