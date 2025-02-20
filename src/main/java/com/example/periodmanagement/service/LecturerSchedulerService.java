package com.example.periodmanagement.service;

import com.example.periodmanagement.dto.PageResponseDto;
import com.example.periodmanagement.dto.lecturerScheduler.LecturerSchedulerDto;
import com.example.periodmanagement.form.lecturerSchduler.CreateLecturerSchedulerForm;
import com.example.periodmanagement.form.lecturerSchduler.UpdateLecturerSchedulerForm;
import com.example.periodmanagement.model.criteria.LecturerSchedulerCriteria;
import org.springframework.data.domain.Pageable;

public interface LecturerSchedulerService {
    LecturerSchedulerDto createLecturerScheduler(CreateLecturerSchedulerForm form);

    LecturerSchedulerDto getLecturerScheduler(Long id);

    PageResponseDto<LecturerSchedulerDto> getPageLecturerScheduler(LecturerSchedulerCriteria criteria, Pageable pageable);

    LecturerSchedulerDto updateLecturerScheduler(UpdateLecturerSchedulerForm form);

    void deleteLecturerScheduler(Long id);
}
