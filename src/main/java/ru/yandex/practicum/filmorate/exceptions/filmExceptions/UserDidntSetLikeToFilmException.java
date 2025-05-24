package ru.yandex.practicum.filmorate.exceptions.filmExceptions;

public class UserDidntSetLikeToFilmException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private long userId;
	private long filmId;
	private String error;

	public UserDidntSetLikeToFilmException(long userId, long filmId, String error) {
		super("Пользователь с id=" + userId + " не ставил лайк фильму с id=" + filmId);
		this.userId = userId;
		this.filmId = filmId;
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public long getUserId() {
		return userId;
	}

	public long getFilmId() {
		return filmId;
	}

}
