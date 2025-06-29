package ru.yandex.practicum.filmorate.model.film;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

class GenreValidation implements ConstraintValidator<GenreValidator, Film> {

	@Override
	public boolean isValid(Film film, ConstraintValidatorContext context) {
		if (film.getGenres() == null) {
			film.setGenres(null);
		}
		return true;
	}
}