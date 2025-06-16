package ru.yandex.practicum.filmorate.exceptions.likesExceptions;

public class UserDidntSetLikeToFilmException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Long filmId;
	private Long userId;
	private String errorMessage;

	public UserDidntSetLikeToFilmException(Long filmId, Long userId, String errorMessage) {
		super("Пользователь с id=" + userId + " не ставил лайк фильму с id=" + filmId);
		this.filmId = filmId;
		this.userId = userId;
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public Long getUserId() {
		return userId;
	}

	public Long getFilmId() {
		return filmId;
	}
}
