package ru.yandex.practicum.filmorate.exceptions.likeExceptions;

public class UserAllreadySetLikeToFilmException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long filmId;
	private String errorMessage;

	public UserAllreadySetLikeToFilmException(Long userId, Long filmId, String errorMessage) {
		super("Пользователь с id=" + userId + " уже ставил лайк фильму с id=" + filmId);
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
