package ru.yandex.practicum.filmorate.exceptions.userExceptions;

public class UserAllreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private long userId;
	private String error;

	public UserAllreadyExistException(long userId, String error) {
		super("Пользователь с id=" + userId + "уже существует");
		this.userId = userId;
		this.error = error;
	}

	public long getUserId() {
		return userId;
	}

	public String getError() {
		return error;
	}

}
