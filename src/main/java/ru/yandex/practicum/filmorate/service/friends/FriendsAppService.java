package ru.yandex.practicum.filmorate.service.friends;

import java.util.List;

import ru.yandex.practicum.filmorate.model.user.User;

public interface FriendsAppService {

	/*
	 * объеденить пользователей в друзья
	 */
	boolean associateUsersAsFriends(Long userOneId, Long userTwoId);

	/*
	 * проверка пользователей, являются ли они друзьями
	 */
	boolean isUsersAreFriends(Long userOneId, Long userTwoId);

	/*
	 * убрать пользователей из списка друзей друг друга
	 */
	boolean disassociateUsersAsFriends(Long userOneId, Long userTwoId);

	/*
	 * очистить список друзей пользователя
	 */
	void disassociateAllFriendsOfUser(Long userId);

	/*
	 * получить список всех друзей (по типу User)
	 */
	List<User> getAllFriendsOfUser(Long userId);

	/*
	 * получить список всех друзей (по типу User)
	 */
	List<User> getCommonFriendsOfUsers(Long userOneId, Long userTwoId);
}