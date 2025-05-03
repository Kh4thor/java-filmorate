package ru.yandex.practicum.filmorate.model.user;

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
@Constraint(validatedBy = BirthdayValidator.class)
public @interface Birthday {

	Class<?>[] groups() default {};

	String message() default "Пользователь не может быть младше 14 лет.";

	Class<? extends Payload>[] payload() default {};
}

class BirthdayValidator implements ConstraintValidator<Birthday, LocalDate> {

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {

		int yearsDifference = LocalDate.now().getYear() - value.getYear();
		int monthsDifference = value.getMonthValue() - LocalDate.now().getMonthValue();
		int daysDifference = value.getDayOfMonth() - LocalDate.now().getDayOfMonth();

		if (yearsDifference > 14) {
			return true;
		}

		else if (yearsDifference == 14) {
			if (monthsDifference > 0) {
				return true;

			} else if (monthsDifference == 0) {
				if (daysDifference >= 0) {
					return true;
				}
			}
		}
		return false;
	}
}