package ru.yandex.practicum.filmorate.exceptions.userExceptions;

import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {

	private static final long serialVersionUID = 1L;
	private Long userId;
	private String errorMessage;

	public UserNotFoundException(Long userId, String errorMessage) {
		super("Пользователь id=" + userId + " не найден");
		this.errorMessage = errorMessage;
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public String geterrorMessage() {
		return errorMessage;
	}

}
