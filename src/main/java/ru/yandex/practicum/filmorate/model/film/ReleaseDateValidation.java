package ru.yandex.practicum.filmorate.model.film;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ReleaseDateValidator.class)
public @interface ReleaseDateValidation {

	Class<?>[] groups() default {};

	String message() default "Дата релиза не может быть раньше 28 декабря 1895 года";

	Class<? extends Payload>[] payload() default {};
}

class ReleaseDateValidator implements ConstraintValidator<ReleaseDateValidation, LocalDate> {
	private static final LocalDate DATE_RELEASE = LocalDate.of(1895, 12, 27);

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		return value == null ? false : value.isAfter(DATE_RELEASE) ? true : false;
	}
}