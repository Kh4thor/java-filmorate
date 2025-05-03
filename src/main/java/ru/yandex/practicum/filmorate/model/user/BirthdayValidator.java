package ru.yandex.practicum.filmorate.model.user;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

class BirthdayValidator implements ConstraintValidator<Birthday, LocalDate> {

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {

		int yearsDifference = LocalDate.now().getYear() - value.getYear();
		int monthsDifference = value.getMonthValue() - LocalDate.now().getMonthValue();
		int daysDifference = value.getDayOfMonth() - LocalDate.now().getDayOfMonth();

		if (yearsDifference > 14) {
			return true;
		} else if (yearsDifference == 14) {
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