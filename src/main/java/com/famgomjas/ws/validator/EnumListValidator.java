package com.famgomjas.ws.validator;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class EnumListValidator implements ConstraintValidator<EnumList, String>{
	private List<String> acceptedValues;

	@Override
	public void initialize(EnumList targetEnum) {
		acceptedValues = Stream.of(targetEnum.targetClassType().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return acceptedValues.contains(value);
	}
}
