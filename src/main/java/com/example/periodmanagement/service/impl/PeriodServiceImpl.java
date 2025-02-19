package com.example.periodmanagement.service.impl;

import com.example.periodmanagement.dto.PageResponseDto;
import com.example.periodmanagement.dto.period.PeriodDto;
import com.example.periodmanagement.enumeration.ErrorCode;
import com.example.periodmanagement.exception.AppException;
import com.example.periodmanagement.form.period.CreatePeriodForm;
import com.example.periodmanagement.form.period.UpdatePeriodForm;
import com.example.periodmanagement.mapper.PeriodMapper;
import com.example.periodmanagement.model.Period;
import com.example.periodmanagement.model.criteria.PeriodCriteria;
import com.example.periodmanagement.repository.PeriodRepository;
import com.example.periodmanagement.service.PeriodService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class PeriodServiceImpl implements PeriodService {

    PeriodRepository periodRepository;
    PeriodMapper periodMapper;

    @Override
    @Transactional
    public PeriodDto createPeriod(CreatePeriodForm form) {
        if (periodRepository.existsByName(form.getName())) {
            throw new AppException(ErrorCode.PERIOD_EXITED);
        }
        Period period = periodMapper.toEntity(form);
        period = periodRepository.save(period);
        return periodMapper.toDto(period);
    }

    @Override
    public PeriodDto getPeriod(Long id) {
        Period period = periodRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PERIOD_NOT_FOUND));
        return periodMapper.toDto(period);
    }

    @Override
    public PageResponseDto<PeriodDto> getPagePeriods(PeriodCriteria periodCriteria, Pageable pageable) {
        Page<Period> pageData = periodRepository.findAll(periodCriteria.getCriteria(), pageable);

        return PageResponseDto.<PeriodDto>builder()
                .currentPage(pageable.getPageNumber())
                .totalPages(pageData.getTotalPages())
                .pageSize(pageable.getPageSize())
                .totalElements(pageData.getTotalElements())
                .data(pageData.getContent().stream()
                        .map(periodMapper::toDto)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    @Transactional
    public PeriodDto updatePeriod(UpdatePeriodForm form) {
        Period period = periodRepository.findById(form.getId())
                .orElseThrow(() -> new AppException(ErrorCode.PERIOD_NOT_FOUND));

        if (periodRepository.existsByName(form.getName()) && !period.getName().equals(form.getName())) {
            throw new AppException(ErrorCode.PERIOD_EXITED);
        }
        periodMapper.update(period, form);
        period = periodRepository.save(period);
        return periodMapper.toDto(period);
    }

    @Override
    @Transactional
    public void deletePeriod(Long id) {
        Period period = periodRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PERIOD_NOT_FOUND));
        periodRepository.delete(period);
    }
}
