package ru.yandex.practicum.filmorate.service.friend;

import java.util.List;
import java.util.Optional;

import ru.yandex.practicum.filmorate.exceptions.FriendServiceException;
import ru.yandex.practicum.filmorate.exceptions.UserServiceException;
import ru.yandex.practicum.filmorate.model.user.User;

public interface FriendsAppService {

	/*
	 * объеденить пользователей в друзья
	 */
	boolean associateUsersAsFriends(long userOneId, long userTwoId) throws FriendServiceException, UserServiceException;

	/*
	 * проверка пользователей, являются ли они друзьями
	 */
	boolean isUsersAreFriends(long userOneId, long userTwoId) throws FriendServiceException, UserServiceException;

	/*
	 * убрать пользователей из списка друзей друг друга
	 */
	void disassociateUsersAsFriends(long userOneId, long userTwoId) throws FriendServiceException, UserServiceException;

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
			throws FriendServiceException, UserServiceException;

	/*
	 * получить друга (по типу User)
	 */
	Optional<User> getFriendOfUser(long userId, long friendId);

}