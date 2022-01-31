package com.grupolemon.ocarsionplus.repository.specification.coche;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.grupolemon.ocarsionplus.model.Coche;
import com.grupolemon.ocarsionplus.model.OperacionEnum;
import com.grupolemon.ocarsionplus.repository.specification.SearchCriteria;

import lombok.AllArgsConstructor;



@AllArgsConstructor
public class CocheFiltroPorMarca implements Specification<Coche> {
	private static final long serialVersionUID = 674311820836093623L;
	
	private SearchCriteria criteria;

    @Override
    public Predicate toPredicate
      (Root<Coche> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
 
        if (criteria.getOperation().equals(OperacionEnum.GT)) {
            return builder.greaterThanOrEqualTo(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } 
        else if (criteria.getOperation().equals(OperacionEnum.LT)) {
            return builder.lessThanOrEqualTo(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } 
        else if (criteria.getOperation().equals(OperacionEnum.EQ)) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                  root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null; 
    }

}
