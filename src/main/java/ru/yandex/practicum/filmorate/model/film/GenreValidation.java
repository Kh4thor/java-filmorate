package ru.yandex.practicum.filmorate.model.film;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

class GenreValidation implements ConstraintValidator<ReleaseDateValidation, Film> {

	@Override
	public boolean isValid(Film film, ConstraintValidatorContext context) {
		if (film.getGenres() == null) {
			List<Genre> genres = new ArrayList<>();
			genres.add(new Genre(0));
			film.setGenres(genres);
			return true;
		}
		if (film.getGenres().contains(id > 2)) {
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