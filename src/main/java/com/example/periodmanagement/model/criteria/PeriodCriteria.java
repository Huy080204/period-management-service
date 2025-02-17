package com.example.periodmanagement.model.criteria;

import com.example.periodmanagement.model.Period;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class PeriodCriteria {
    Long id;
    String name;
    LocalDate description;

    public Specification<com.example.periodmanagement.model.Period> getCriteria() {
        return new Specification<Period>() {
            @Override
            public Predicate toPredicate(Root<Period> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if (getName() != null && !getName().trim().isEmpty()) {
                    predicates.add(cb.like(root.get("fullName"), "%" + getName() + "%"));
                }
                if (getDescription() != null) {
                    predicates.add(cb.equal(root.get("birthDate"), getDescription()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
