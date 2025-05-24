package ru.yandex.practicum.filmorate.exceptions.userExceptions;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private long userId;
	private String error;

	public UserNotFoundException(long userId, String error) {
		super("Пользователь c id=" + userId + " не найден");
		this.error = error;
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}

	public String getError() {
		return error;
	}

}
