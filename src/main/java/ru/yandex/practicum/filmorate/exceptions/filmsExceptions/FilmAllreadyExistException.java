package ru.yandex.practicum.filmorate.exceptions.filmsExceptions;

public class FilmAllreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Long filmId;
	private String errorMessage;

	public FilmAllreadyExistException(Long filmId, String errorMessage) {
		super("Фильм id=" + filmId + " уже существует");
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
