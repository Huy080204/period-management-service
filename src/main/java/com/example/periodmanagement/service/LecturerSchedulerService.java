package com.example.periodmanagement.service;

import com.example.periodmanagement.dto.PageResponseDto;
import com.example.periodmanagement.dto.lecturerScheduler.LecturerSchedulerDto;
import com.example.periodmanagement.form.LecturerSchduler.CreateLecturerSchedulerForm;
import com.example.periodmanagement.form.LecturerSchduler.UpdateLecturerSchedulerForm;
import com.example.periodmanagement.model.criteria.LecturerSchedulerCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

public interface LecturerSchedulerService {
    LecturerSchedulerDto createLecturerScheduler(CreateLecturerSchedulerForm form);
    PageResponseDto<LecturerSchedulerDto> getPageLecturerScheduler(LecturerSchedulerCriteria criteria, Pageable pageable);
    LecturerSchedulerDto updateLecturerScheduler(UpdateLecturerSchedulerForm form);
    void deleteLecturerScheduler(Long id);
}
