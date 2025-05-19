package ru.yandex.practicum.filmorate.service;

import java.util.List;
import java.util.Optional;

import ru.yandex.practicum.filmorate.exceptions.FriendServiceException;
import ru.yandex.practicum.filmorate.exceptions.UserStorageException;
import ru.yandex.practicum.filmorate.model.user.User;

public interface FriendsAppService {

	/*
	 * объеденить пользователей в друзья
	 */
	boolean associateUsersAsFriends(long userOneId, long userTwoId) throws FriendServiceException, UserStorageException;

	/*
	 * проверка пользователей, являются ли они друзьями
	 */
	boolean isUsersAreFriends(long userOneId, long userTwoId) throws FriendServiceException, UserStorageException;

	/*
	 * убрать пользователей из списка друзей друг друга
	 */
	void disassociateUsersAsFriends(long userOneId, long userTwoId) throws FriendServiceException, UserStorageException;

	/*
	 * очистить список друзей пользователя
	 */
	void disassociateAllFriendsOfUser(long userId);

	/*
	 * получить список всех друзей (по типу User)
	 */
	List<User> getAllFriendsOfUserById(long userId);

	/*
	 * получить список всех друзей (по типу User)
	 */
	List<User> getCommonFriendsOfUsers(long userOneId, long userTwoId)
			throws FriendServiceException, UserStorageException;

	/*
	 * получить друга (по типу User)
	 */
	Optional<User> getFriendOfUser(long userId, long friendId);

}