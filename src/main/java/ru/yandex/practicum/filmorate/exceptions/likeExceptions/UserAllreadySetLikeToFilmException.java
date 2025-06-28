package ru.yandex.practicum.filmorate.exceptions.likeExceptions;

public class UserAllreadySetLikeToFilmException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Long filmId;
	private Long userId;
	private String errorMessage;

	public UserAllreadySetLikeToFilmException(Long filmId, Long userId, String errorMessage) {
		super("Пользователь id=" + userId + " уже ставил лайк фильму id=" + filmId);
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
