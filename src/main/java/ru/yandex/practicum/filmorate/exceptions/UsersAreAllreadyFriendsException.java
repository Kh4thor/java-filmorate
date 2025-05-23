package ru.yandex.practicum.filmorate.exceptions;

public class UsersAreAllreadyFriendsException extends Exception {

	private static final long serialVersionUID = 1L;
	private long userOneId;
	private long userTwoId;

	public UsersAreAllreadyFriendsException(long userOneId, long userTwoId) {
		super("Пользователь с id=" + userOneId + " и пользователь с id=" + userTwoId + " уже друзья");
		this.userOneId = userOneId;
		this.userTwoId = userTwoId;
	}

	public long getUserOneId() {
		return userOneId;
	}

	public long getUserTwoId() {
		return userTwoId;
	}
}
