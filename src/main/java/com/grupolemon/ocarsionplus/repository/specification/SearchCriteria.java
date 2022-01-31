package com.grupolemon.ocarsionplus.repository.specification;

import com.grupolemon.ocarsionplus.model.OperacionEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchCriteria {

	private String key;
	private OperacionEnum operation;
	private Object value;
}
