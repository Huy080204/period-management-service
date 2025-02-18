package com.example.periodmanagement.service.impl;

import com.example.periodmanagement.dto.PageResponseDto;
import com.example.periodmanagement.dto.lecturerScheduler.LecturerSchedulerDto;
import com.example.periodmanagement.dto.period.PeriodDto;
import com.example.periodmanagement.enumeration.ErrorCode;
import com.example.periodmanagement.exception.AppException;
import com.example.periodmanagement.form.LecturerSchduler.CreateLecturerSchedulerForm;
import com.example.periodmanagement.form.LecturerSchduler.UpdateLecturerSchedulerForm;
import com.example.periodmanagement.mapper.LecturerSchedulerMapper;
import com.example.periodmanagement.model.LecturerScheduler;
import com.example.periodmanagement.model.Period;
import com.example.periodmanagement.model.criteria.LecturerSchedulerCriteria;
import com.example.periodmanagement.repository.LecturerSchedulerRepository;
import com.example.periodmanagement.repository.PeriodRepository;
import com.example.periodmanagement.service.LecturerSchedulerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class LecturerSchedulerServiceImpl implements LecturerSchedulerService {

    LecturerSchedulerRepository lecturerSchedulerRepository;
    PeriodRepository periodRepository;
    LecturerSchedulerMapper lecturerSchedulerMapper;

    @Override
    public LecturerSchedulerDto createLecturerScheduler(CreateLecturerSchedulerForm form) {
        Period period = periodRepository.findById(form.getPeriodId())
                .orElseThrow(() -> new RuntimeException("Period not found"));
        LecturerScheduler lecturerScheduler = lecturerSchedulerMapper.toEntity(form);
        lecturerScheduler.setPeriod(period);
        lecturerScheduler = lecturerSchedulerRepository.save(lecturerScheduler);
        return lecturerSchedulerMapper.toDto(lecturerScheduler);
    }

    @Override
    public PageResponseDto<LecturerSchedulerDto> getPageLecturerScheduler(LecturerSchedulerCriteria criteria, Pageable pageable) {
        Page<LecturerScheduler> pageData = lecturerSchedulerRepository.findAll(criteria.getCriteria(), pageable);
        return PageResponseDto.<LecturerSchedulerDto>builder()
                .currentPage(pageable.getPageNumber())
                .totalPages(pageData.getTotalPages())
                .pageSize(pageable.getPageSize())
                .totalElements(pageData.getTotalElements())
                .data(pageData.getContent().stream()
                        .map(lecturerSchedulerMapper::toDto)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public LecturerSchedulerDto updateLecturerScheduler(UpdateLecturerSchedulerForm form) {
        LecturerScheduler lecturerScheduler = lecturerSchedulerRepository.findById(form.getLectureSchedulerId())
                .orElseThrow(() -> new AppException(ErrorCode.LECTURER_SCHEDULER_NOT_FOUND));
        lecturerSchedulerMapper.update(lecturerScheduler, form);
        if (!Objects.equals(lecturerScheduler.getPeriod().getId(), form.getPeriodId())) {
            Period period = periodRepository.findById(form.getPeriodId())
                    .orElseThrow(() -> new AppException(ErrorCode.PERIOD_NOT_FOUND));
            lecturerScheduler.setPeriod(period);
        }
        return lecturerSchedulerMapper.toDto(lecturerSchedulerRepository.save(lecturerScheduler));
    }

    @Override
    public void deleteLecturerScheduler(Long id) {
        LecturerScheduler lecturerScheduler = lecturerSchedulerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.LECTURER_SCHEDULER_NOT_FOUND));
        lecturerSchedulerRepository.delete(lecturerScheduler);
    }
}
