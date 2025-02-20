package com.example.periodmanagement.service;

import com.example.periodmanagement.dto.PageResponseDto;
import com.example.periodmanagement.dto.period.PeriodDto;
import com.example.periodmanagement.form.period.CreatePeriodForm;
import com.example.periodmanagement.form.period.UpdatePeriodForm;
import com.example.periodmanagement.model.criteria.PeriodCriteria;
import org.springframework.data.domain.Pageable;

public interface PeriodService {
    PeriodDto createPeriod(CreatePeriodForm form);

    PeriodDto getPeriod(Long id);

    PageResponseDto<PeriodDto> getPagePeriods(PeriodCriteria periodCriteria, Pageable pageable);

    PeriodDto updatePeriod(UpdatePeriodForm form);

    void deletePeriod(Long id);

}
