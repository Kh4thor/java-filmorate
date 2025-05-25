package ru.yandex.practicum.filmorate.service.friends;

import java.util.List;

import ru.yandex.practicum.filmorate.model.user.User;

public interface FriendsAppService {

	/*
	 * объеденить пользователей в друзья
	 */
	boolean associateUsersAsFriends(long userOneId, long userTwoId);

	/*
	 * проверка пользователей, являются ли они друзьями
	 */
	boolean isUsersAreFriends(long userOneId, long userTwoId);

	/*
	 * убрать пользователей из списка друзей друг друга
	 */
	boolean disassociateUsersAsFriends(long userOneId, long userTwoId);

	/*
	 * очистить список друзей пользователя
	 */
	void disassociateAllFriendsOfUser(long userId);

	/*
	 * получить список всех друзей (по типу User)
	 */
	List<User> getAllFriendsOfUser(long userId);

	/*
	 * получить список всех друзей (по типу User)
	 */
	List<User> getCommonFriendsOfUsers(long userOneId, long userTwoId);
}