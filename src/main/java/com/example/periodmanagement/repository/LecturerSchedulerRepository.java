package com.example.periodmanagement.repository;

import com.example.periodmanagement.model.LecturerScheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerSchedulerRepository extends JpaRepository<LecturerScheduler, Long>, JpaSpecificationExecutor<LecturerScheduler> {

}
