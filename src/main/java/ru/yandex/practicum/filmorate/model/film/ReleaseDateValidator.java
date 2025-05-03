package ru.yandex.practicum.filmorate.model.film;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

class ReleaseDateValidator implements ConstraintValidator<ReleaseDateValidation, LocalDate> {
	private static final LocalDate DATE_RELEASE = LocalDate.of(1895, 12, 27);

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		return value == null ? false : value.isAfter(DATE_RELEASE) ? true : false;
	}
}