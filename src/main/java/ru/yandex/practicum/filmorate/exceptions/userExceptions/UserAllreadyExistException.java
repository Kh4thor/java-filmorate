package ru.yandex.practicum.filmorate.exceptions.userExceptions;

public class UserAllreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Long userId;
	private String errorMessage;

	public UserAllreadyExistException(Long userId, String errorMessage) {
		super("Пользователь id=" + userId + " уже существует");
		this.userId = userId;
		this.errorMessage = errorMessage;
	}

	public Long getUserId() {
		return userId;
	}

	public String geterrorMessage() {
		return errorMessage;
	}

}
