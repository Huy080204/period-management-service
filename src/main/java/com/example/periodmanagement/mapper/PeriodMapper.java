package com.example.periodmanagement.mapper;

import com.example.periodmanagement.dto.period.PeriodDto;
import com.example.periodmanagement.form.period.CreatePeriodForm;
import com.example.periodmanagement.form.period.UpdatePeriodForm;
import com.example.periodmanagement.model.Period;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PeriodMapper {

    Period toEntity(CreatePeriodForm form);

    @Mapping(source = "id", target = "periodId")
    PeriodDto toDto(Period period);

    void update(@MappingTarget Period period, UpdatePeriodForm form);

}
