package ru.yandex.practicum.filmorate.exceptions.userExceptions;

public class UsersAreNotFriendsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Long userOneId;
	private Long userTwoId;
	private String errorMessage;

	public UsersAreNotFriendsException(Long userOneId, Long userTwoId, String errorMessage) {
		super("Пользователь с id=" + userOneId + " и пользователь с id=" + userTwoId + " не являются друзьями");
		this.userOneId = userOneId;
		this.userTwoId = userTwoId;
	}

	public Long getUserOneId() {
		return userOneId;
	}

	public Long getUserTwoId() {
		return userTwoId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
