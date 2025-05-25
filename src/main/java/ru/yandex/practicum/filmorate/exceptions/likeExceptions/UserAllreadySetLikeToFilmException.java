package ru.yandex.practicum.filmorate.exceptions.likeExceptions;

public class UserAllreadySetLikeToFilmException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private long userId;
	private long filmId;
	private String error;

	public UserAllreadySetLikeToFilmException(long userId, long filmId, String error) {
		super("Пользователь с id=" + userId + " уже ставил лайк фильму с id=" + filmId);
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
