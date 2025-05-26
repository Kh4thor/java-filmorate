package ru.yandex.practicum.filmorate.exceptions.likesExceptions;

public class UserDidntSetLikeToFilmException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long filmId;
	private String errorMessage;

	public UserDidntSetLikeToFilmException(Long userId, Long filmId, String errorMessage) {
		super("Пользователь с id=" + userId + " не ставил лайк фильму с id=" + filmId);
		this.userId = userId;
		this.filmId = filmId;
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
