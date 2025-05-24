package ru.yandex.practicum.filmorate.exceptions.userExceptions;

public class UsersAreAllreadyFriendsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private long userOneId;
	private long userTwoId;
	private String error;

	public UsersAreAllreadyFriendsException(long userOneId, long userTwoId, String error) {
		super("Пользователь с id=" + userOneId + " и пользователь с id=" + userTwoId + " уже друзья");
		this.userOneId = userOneId;
		this.userTwoId = userTwoId;
		this.error = error;
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
