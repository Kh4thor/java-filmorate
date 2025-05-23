package ru.yandex.practicum.filmorate.controller.friend;

public interface FriendAppController<T> {

	/*
	 * добавить пользователей друг другу в друзья
	 */
	public void associateUsersAsFriends(long userOneId, long userTwoId);

}
