package ru.yandex.practicum.filmorate.exceptions;

public class UsersAreNotFriendsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private long userOneId;
	private long userTwoId;
	

	public UsersAreNotFriendsException (long userOneId, long userTwoId) {
		super("Пользователь с id=" + userOneId + " и пользователь с id=" + userTwoId + " не являются друзьями" );
		this.userOneId = userOneId;
		this.userTwoId = userTwoId;
	}
	
	public long getUserOneId () {
		return userOneId;
	}
	
	public long getUserTwoId () {
		return userTwoId;
	}
}
