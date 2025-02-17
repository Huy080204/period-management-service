package com.example.periodmanagement.form.period;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class UpdatePeriodForm {

    @NotNull(message = "Period id cannot be null or empty")
    Long id;

    @NotBlank(message = "Period name cannot be null or empty")
    String name;

    @NotBlank(message = "Period description cannot be null or empty")
    String description;

    @NotNull
    @Past
    LocalDate startDate;

    @NotNull
    @Past
    LocalDate dueDate;

    @NotNull
    Integer state;
}
