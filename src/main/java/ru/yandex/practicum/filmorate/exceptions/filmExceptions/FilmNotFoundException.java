package ru.yandex.practicum.filmorate.exceptions.filmExceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FilmNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Long filmId;
	private String errorMessage;

	public FilmNotFoundException(Long filmId, String errorMessage) {
		super("Фильм с id=" + filmId + " не найден");
		this.filmId = filmId;
		this.errorMessage = errorMessage;
		log.warn(errorMessage + "");
	}

	public Long getFilmId() {
		return filmId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
