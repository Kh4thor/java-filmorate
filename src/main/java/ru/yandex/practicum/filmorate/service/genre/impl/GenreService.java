package ru.yandex.practicum.filmorate.service.genre.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.ExceptionAppChecker;
import ru.yandex.practicum.filmorate.model.film.Genre;
import ru.yandex.practicum.filmorate.service.genre.GenreAppService;
import ru.yandex.practicum.filmorate.storage.genre.GenreAppStorage;

@Service
public class GenreService implements GenreAppService<Genre> {

	private final GenreAppStorage<Genre> genreAppStorage;
	private final ExceptionAppChecker exceptionsAppChecker;

	public GenreService(ExceptionAppChecker exceptionsAppChecker, GenreAppStorage<Genre> genreAppStorage) {
		this.genreAppStorage = genreAppStorage;
		this.exceptionsAppChecker = exceptionsAppChecker;
	}

	@Override
	public Genre getGenre(int genreId) {
		String errorMessage = "Невозможно получить жанр фильма";
		exceptionsAppChecker.checkGenreValueIsOutOfRangeException(genreId, errorMessage);
		return genreAppStorage.getGenre(genreId);
	}

	@Override
	public List<Genre> getAllGenres() {
		return genreAppStorage.getAllGenres();
	}
}
