package ru.yandex.practicum.filmorate.exceptions.userExceptions;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Long userId;
	private String errorMessage;

	public UserNotFoundException(Long userId, String errorMessage) {
		super("Пользователь c id=" + userId + " не найден");
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
