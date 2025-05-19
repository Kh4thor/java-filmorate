package ru.yandex.practicum.filmorate.controller;

import ru.yandex.practicum.filmorate.exceptions.FriendServiceException;
import ru.yandex.practicum.filmorate.exceptions.UserServiceException;

public interface FriendAppController<T> {

	/*
	 * добавить пользователей друг другу в друзья
	 */
	public void associateUsersAsFriends(long userOneId, long userTwoId) throws FriendServiceException, UserServiceException;

}
