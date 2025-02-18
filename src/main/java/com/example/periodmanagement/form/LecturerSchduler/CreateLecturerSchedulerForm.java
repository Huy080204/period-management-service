package com.example.periodmanagement.form.LecturerSchduler;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateLecturerSchedulerForm {
    @NotNull(message = "Lecture id can not be null or empty")
    Long lecturerId;
    @NotNull(message = "Subject id can not be null or empty")
    Long subjectId;
    @NotNull(message = "Period id can not be null or empty")
    Long periodId;
}
