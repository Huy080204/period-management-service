package com.example.periodmanagement.dto.lecturerScheduler;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Builder
public class LecturerSchedulerDto {
    Long lecturerSchedulerId;
    Long lecturerId;
    Long subjectId;
    Long periodId;
}
