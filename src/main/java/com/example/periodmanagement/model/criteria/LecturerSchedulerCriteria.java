package com.example.periodmanagement.model.criteria;

import com.example.periodmanagement.model.LecturerScheduler;
import com.example.periodmanagement.model.Period;
import jakarta.persistence.criteria.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class LecturerSchedulerCriteria {
    Long lecturerSchedulerId;
    Long lecturerId;
    Long subjectId;
    Long periodId;

    public Specification<LecturerScheduler> getCriteria() {
        return new Specification<LecturerScheduler>() {
            @Override
            public Predicate toPredicate(Root<LecturerScheduler> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (getLecturerSchedulerId() != null) {
                    predicates.add(cb.equal(root.get("id"), getLecturerSchedulerId()));
                }
                if (getLecturerId() != null) {
                    predicates.add(cb.equal(root.get("lecturerId"), getLecturerId()));
                }
                if (getSubjectId() != null) {
                    predicates.add(cb.equal(root.get("subjectId"), getSubjectId()));
                }
                if (getPeriodId() != null) {
                    Join<LecturerScheduler, Period> lecturerSchedulerPeriodJoin = root.join("period");
                    predicates.add(cb.equal(lecturerSchedulerPeriodJoin.get("id"), getPeriodId()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
