package com.example.periodmanagement.dto.period;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
public class PeriodDto {
    Long periodId;
    String name;
    String description;
    LocalDate startDate;
    LocalDate dueDate;
    Integer state;
}
