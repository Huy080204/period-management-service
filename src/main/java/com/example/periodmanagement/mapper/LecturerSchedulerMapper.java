package com.example.periodmanagement.mapper;

import com.example.periodmanagement.dto.lecturerScheduler.LecturerSchedulerDto;
import com.example.periodmanagement.form.LecturerSchduler.CreateLecturerSchedulerForm;
import com.example.periodmanagement.form.LecturerSchduler.UpdateLecturerSchedulerForm;
import com.example.periodmanagement.model.LecturerScheduler;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LecturerSchedulerMapper {

    LecturerScheduler toEntity(CreateLecturerSchedulerForm form);

    @Mapping(source = "id", target = "lecturerSchedulerId")
    @Mapping(source = "period.id", target = "periodId")
    LecturerSchedulerDto toDto(LecturerScheduler lecturerScheduler);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "period", ignore = true)
    void update(@MappingTarget LecturerScheduler lecturerScheduler, UpdateLecturerSchedulerForm form);
}
