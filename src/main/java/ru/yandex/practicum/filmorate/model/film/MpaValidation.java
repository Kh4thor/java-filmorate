package ru.yandex.practicum.filmorate.model.film;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

class MpaValidation implements ConstraintValidator<ReleaseDateValidation, Film> {

	@Override
	public boolean isValid(Film film, ConstraintValidatorContext context) {
		if (film.getMpa() == null) {
			film.setMpa(new Mpa(0));
			return true;
		}
		if (film.getMpa().getId() > 6) {
			{
				return false;
			}
		}
		if (film.getMpa().getId() < 0) {
			return false;
		}
		return true;
	}
}