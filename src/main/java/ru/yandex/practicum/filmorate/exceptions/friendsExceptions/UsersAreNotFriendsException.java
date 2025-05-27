package ru.yandex.practicum.filmorate.exceptions.friendsExceptions;

public class UsersAreNotFriendsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Long userOneId;
	private Long userTwoId;
	private String errorMessage;

	public UsersAreNotFriendsException(Long userOneId, Long userTwoId, String errorMessage) {
		super("Пользователи id=" + userOneId + " и id=" + userTwoId + " не являются друзьями");
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
