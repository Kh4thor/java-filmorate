package ru.yandex.practicum.filmorate.model.genre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.model.film.Film;

class GenreValidation implements ConstraintValidator<GenreValidator, Film> {

	@Override
	public boolean isValid(Film film, ConstraintValidatorContext context) {
		if (film.getGenres() == null) {
			film.setGenres(new ArrayList<>());
		}
		/*
		 * сортировка для поддержания уникальности id-жанров
		 */
		List<Genre> genreList = film.getGenres().stream()
				.collect(Collectors.toMap(Genre::getId, genre -> genre, (g1, g2) -> g1)).values().stream().toList();
		film.setGenres(genreList);
		return true;
	}
}