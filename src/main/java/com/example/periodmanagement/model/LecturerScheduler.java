package com.example.periodmanagement.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class LecturerScheduler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long lecturerId;

    Long subjectId;

    @ManyToOne
    @JoinColumn(nullable = false)
    Period period;
}
