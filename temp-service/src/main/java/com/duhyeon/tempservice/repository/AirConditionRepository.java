package com.duhyeon.tempservice.repository;

import com.duhyeon.tempservice.domain.AirCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirConditionRepository extends JpaRepository<AirCondition, Long> {
}
