package ru.yandex.practicum.filmorate.service.friends.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.impl.ExceptionsChecker;
import ru.yandex.practicum.filmorate.exceptions.friendsExceptions.UsersAreNotFriendsException;
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
	public boolean associateUsersAsFriends(Long userOneId, Long userTwoId) {
		String errorMessage = "Невозможно добавить пользователя в друзья";
		exceptionsChecker.checkUsersAreFriendsException(userOneId, userTwoId, errorMessage);
		exceptionsChecker.checkUserNotFoundException(userOneId, errorMessage);
		exceptionsChecker.checkUserNotFoundException(userTwoId, errorMessage);
		friendsAppStorage.associateUsersAsFriends(userOneId, userTwoId);
		return true;
	}

	/*
	 * проверка пользователей, являются ли они друзьями (by user's id)
	 */
	@Override
	public boolean isUsersAreFriends(Long userOneId, Long userTwoId) {
		return friendsAppStorage.isUsersAssociatedAsFriends(userOneId, userTwoId);
	}

	/*
	 * убрать пользователей из списка друзей друг друга
	 */
	@Override
	public boolean disassociateUsersAsFriends(Long userOneId, Long userTwoId) {
		String errorMessage = "Невозможно удалить пользователя из друзей";
		exceptionsChecker.checkUserNotFoundException(userOneId, errorMessage);
		exceptionsChecker.checkUserNotFoundException(userTwoId, errorMessage);
		friendsAppStorage.disassociateUserAsFriends(userOneId, userTwoId);
		return true;
	}

	/*
	 * очистить список друзей пользователя
	 */
	@Override
	public void disassociateAllFriendsOfUser(Long userId) {
		String errorMessage = "Невозможно удалить друзей пользователя";
		exceptionsChecker.checkUserNotFoundException(userId, errorMessage);
		friendsAppStorage.removeAllAssociatedFriendsOfUser(userId);
	}

	/*
	 * получить список всех друзей (по типу User)
	 */
	@Override
	public List<User> getAllFriendsOfUser(Long userId) {
		String errorMessage = "Невозможно получить список друзей пользователя";
		exceptionsChecker.checkUserNotFoundException(userId, errorMessage);
		List<Long> friendsIdList = friendsAppStorage.getIdListOfAssociatedFriends(userId);

		return friendsIdList
				.stream()
				// для каждого id из списка friendsIdList вытаскиваем пользователя из userStorage
				.map(id -> usersAppStorage.getUser(id)) 
				.toList();
	}

	@Override
	public List<User> getCommonFriendsOfUsers(Long userOneId, Long userTwoId) throws UsersAreNotFriendsException {
		String errorMessage = "Невозможно получить список друзей пользователя";
		exceptionsChecker.checkUserNotFoundException(userOneId, errorMessage);
		exceptionsChecker.checkUserNotFoundException(userTwoId, errorMessage);
		exceptionsChecker.checkUsersAreNotFriendsException(userOneId, userTwoId, errorMessage);
		List<Long> commonFriendsIdList = friendsAppStorage.geIdListOfCommonFriends(userOneId, userTwoId);

		return commonFriendsIdList
				.stream()
				// для каждого id из списка friendsIdList вытаскиваем пользоватедя из userStorage
				.map(id -> usersAppStorage.getUser(id)) 
				.toList();
	}
}
