package com.example.periodmanagement.repository;

import com.example.periodmanagement.model.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PeriodRepository extends JpaRepository<Period, Long>, JpaSpecificationExecutor<Period> {
    boolean existsByName(String name);
}
