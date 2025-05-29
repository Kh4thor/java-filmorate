package ru.yandex.practicum.filmorate.exceptions.filmsExceptions;

import java.util.NoSuchElementException;

public class FilmNotFoundException extends NoSuchElementException {

	private static final long serialVersionUID = 1L;
	private Long filmId;
	private String errorMessage;

	public FilmNotFoundException(Long filmId, String errorMessage) {
		super("Фильм id=" + filmId + " не найден.");
		this.filmId = filmId;
		this.errorMessage = errorMessage;
	}

	public Long getFilmId() {
		return filmId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
