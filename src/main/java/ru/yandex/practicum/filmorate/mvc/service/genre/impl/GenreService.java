package ru.yandex.practicum.filmorate.mvc.service.genre.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.ExceptionAppChecker;
import ru.yandex.practicum.filmorate.model.genre.Genre;
import ru.yandex.practicum.filmorate.mvc.service.genre.GenreAppService;
import ru.yandex.practicum.filmorate.mvc.storage.genre.GenreAppStorage;

@Service
@Slf4j
public class GenreService implements GenreAppService {

	private final GenreAppStorage genreAppStorage;
	private final ExceptionAppChecker exceptionsAppChecker;

	public GenreService(ExceptionAppChecker exceptionsAppChecker,
			@Qualifier("dbGenreStorage") GenreAppStorage genreAppStorage) {
		this.genreAppStorage = genreAppStorage;
		this.exceptionsAppChecker = exceptionsAppChecker;
	}

	@Override
	public Genre getGenre(int genreId) {
		String errorMessage = "Невозможно получить жанр фильма";
		exceptionsAppChecker.checkGenreNotFoundException(genreId, errorMessage);
		exceptionsAppChecker.checkGenreValueIsOutOfRangeException(genreId, errorMessage);
		Genre genre = genreAppStorage.getGenre(genreId);
		log.info("Получен жанр " + genre);
		return genre;
	}

	@Override
	public List<Genre> getAllGenres() {
		List<Genre> genresList = genreAppStorage.getAllGenres();
		log.info("Получен список жанров " + genresList);
		return genresList;
	}
}
