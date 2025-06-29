package ru.yandex.practicum.filmorate.model.film;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullCheckMpaAndGenreValidator implements ConstraintValidator<NullCheckMpaAndGenreValidation, Film> {

	@Override
	public boolean isValid(Film film, ConstraintValidatorContext context) {
		if (film.getGenres() == null) {
			List<Integer> id = new ArrayList<>();
			id.add(0);
			film.setGenres(genres);
		}
		if (film.getMpa() == null) {
			film.setMpa(new Mpa(0));
		}
		return true;
	}
}