package ru.yandex.practicum.filmorate.exceptions.filmExceptions;

public class FilmAllreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private long filmId;
	private String error;

	public FilmAllreadyExistException(long filmId, String error) {
		super("Фильм с id=" + filmId + " уже существует");
		this.filmId = filmId;
		this.error = error;
	}

	public long getFilmId() {
		return filmId;
	}

	public String getError() {
		return error;
	}

}
