package com.example.periodmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    String name;

    @Column(columnDefinition = "LONGTEXT")
    String description;

    LocalDate startDate;

    LocalDate dueDate;

    Integer state;

    @JsonIgnore
    @OneToMany(mappedBy = "period", cascade = CascadeType.ALL, orphanRemoval = true)
    List<LecturerScheduler> lecturerSchedulers = new ArrayList<>();
}
