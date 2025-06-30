package ru.yandex.practicum.filmorate.model.mpa;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.model.film.Film;

class MpaValidation implements ConstraintValidator<MpaValidator, Film> {

	@Override
	public boolean isValid(Film film, ConstraintValidatorContext context) {
		if (film.getMpa() == null) {
			film.setMpa(new Mpa());
		}
		return true;
	}
}