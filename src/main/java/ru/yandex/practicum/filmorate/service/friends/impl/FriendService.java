package ru.yandex.practicum.filmorate.service.friends.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.impl.ExceptionsChecker;
import ru.yandex.practicum.filmorate.exceptions.userExceptions.UsersAreNotFriendsException;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.service.friends.FriendsAppService;
import ru.yandex.practicum.filmorate.storage.friends.FriendsAppStorage;
import ru.yandex.practicum.filmorate.storage.users.UsersAppStorage;

@Service
public class FriendService implements FriendsAppService {

	private final UsersAppStorage<User> usersAppStorage;
	private final FriendsAppStorage friendsAppStorage;
	private final ExceptionsChecker exceptionsChecker;

	public FriendService(UsersAppStorage<User> usersAppStorage, FriendsAppStorage friendsAppStorage,
			ExceptionsChecker exceptionsChecker) {
		this.friendsAppStorage = friendsAppStorage;
		this.usersAppStorage = usersAppStorage;
		this.exceptionsChecker = exceptionsChecker;
	}

	/*
	 * объеденить пользователей в друзей
	 */
	@Override
	public boolean associateUsersAsFriends(long userOneId, long userTwoId) {
		String error = "Невозможно добавить пользователя в друзья";
		exceptionsChecker.checkUsersAreFriendsException(userOneId, userTwoId, error);
		exceptionsChecker.checkUserNotFoundException(userOneId, error);
		exceptionsChecker.checkUserNotFoundException(userTwoId, error);
		friendsAppStorage.associateUsersAsFriends(userOneId, userTwoId);
		return true;
	}

	/*
	 * проверка пользователей, являются ли они друзьями (by user's id)
	 */
	@Override
	public boolean isUsersAreFriends(long userOneId, long userTwoId) {
		return friendsAppStorage.isUsersAssociatedAsFriends(userOneId, userTwoId);
	}

	/*
	 * убрать пользователей из списка друзей друг друга
	 */
	@Override
	public boolean disassociateUsersAsFriends(long userOneId, long userTwoId) {
		String error = "Невозможно удалить пользователя из друзей";
		exceptionsChecker.checkUserNotFoundException(userOneId, error);
		exceptionsChecker.checkUserNotFoundException(userTwoId, error);
		friendsAppStorage.disassociateUserAsFriends(userOneId, userTwoId);
		return true;
	}

	/*
	 * очистить список друзей пользователя
	 */
	@Override
	public void disassociateAllFriendsOfUser(long userId) {
		String error = "Невозможно удалить друзей пользователя";
		exceptionsChecker.checkUserNotFoundException(userId, error);
		friendsAppStorage.removeAllAssociatedFriendsOfUser(userId);
	}

	/*
	 * получить список всех друзей (по типу User)
	 */
	@Override
	public List<User> getAllFriendsOfUser(long userId) {
		List<Long> friendsIdList = friendsAppStorage.getIdListOfAssociatedFriends(userId);

		return friendsIdList
				.stream()
				.map(id -> usersAppStorage.getUser(id)) // для каждого id из списка friendsIdList вытаскиваем пользователя из userStorage
				.toList();
	}

	@Override
	public List<User> getCommonFriendsOfUsers(long userOneId, long userTwoId) throws UsersAreNotFriendsException {
		List<Long> commonFriendsIdList = friendsAppStorage.geIdListOfCommonFriends(userOneId, userTwoId);

		return commonFriendsIdList
				.stream()
				.map(id -> usersAppStorage.getUser(id)) // для каждого id из списка friendsIdList вытаскиваем пользоватедя из userStorage
				.toList();
	}
}
