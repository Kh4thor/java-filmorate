package ru.yandex.practicum.filmorate.exceptions.userExceptions;

public class UsersAreNotFriendsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private long userOneId;
	private long userTwoId;
	private String error;

	public UsersAreNotFriendsException(long userOneId, long userTwoId, String error) {
		super("Пользователь с id=" + userOneId + " и пользователь с id=" + userTwoId + " не являются друзьями");
		this.userOneId = userOneId;
		this.userTwoId = userTwoId;
	}

	public long getUserOneId() {
		return userOneId;
	}

	public long getUserTwoId() {
		return userTwoId;
	}

	public String getError() {
		return error;
	}
}
