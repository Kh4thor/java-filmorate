package ru.yandex.practicum.filmorate.exceptions.friendsExceptions;

public class UsersAreAllreadyFriendsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Long userOneId;
	private Long userTwoId;
	private String errorMessage;

	public UsersAreAllreadyFriendsException(Long userOneId, Long userTwoId, String errorMessage) {
		super("Пользователи id=" + userOneId + " и id=" + userTwoId + " уже друзья");
		this.userOneId = userOneId;
		this.userTwoId = userTwoId;
		this.errorMessage = errorMessage;
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
