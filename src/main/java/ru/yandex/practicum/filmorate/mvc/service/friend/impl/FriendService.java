package ru.yandex.practicum.filmorate.mvc.service.friend.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.impl.ExceptionChecker;
import ru.yandex.practicum.filmorate.exceptions.friendExceptions.UsersAreNotFriendsException;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.mvc.service.friend.FriendAppService;
import ru.yandex.practicum.filmorate.mvc.storage.friend.FriendAppStorage;

@Service
public class FriendService implements FriendAppService {

	private final FriendAppStorage friendAppStorage;
	private final ExceptionChecker exceptionsChecker;

	public FriendService(@Qualifier("dbFriendStorage") FriendAppStorage friendAppStorage,
			ExceptionChecker exceptionsChecker) {
		this.friendAppStorage = friendAppStorage;
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
		return friendAppStorage.associateUsersAsFriends(userOneId, userTwoId);
	}

	/*
	 * проверка пользователей, являются ли они друзьями (by user's id)
	 */
	@Override
	public boolean isUsersAreFriends(Long userOneId, Long userTwoId) {
		return friendAppStorage.isUsersAssociatedAsFriends(userOneId, userTwoId);
	}

	/*
	 * убрать пользователей из списка друзей друг друга
	 */
	@Override
	public boolean disassociateUsersAsFriends(Long userOneId, Long userTwoId) {
		String errorMessage = "Невозможно удалить пользователя из друзей";
		exceptionsChecker.checkUserNotFoundException(userOneId, errorMessage);
		exceptionsChecker.checkUserNotFoundException(userTwoId, errorMessage);
		return friendAppStorage.disassociateUserAsFriends(userOneId, userTwoId);
	}

	/*
	 * очистить список друзей пользователя
	 */
	@Override
	public void disassociateAllFriendsOfUser(Long userId) {
		String errorMessage = "Невозможно удалить друзей пользователя";
		exceptionsChecker.checkUserNotFoundException(userId, errorMessage);
		friendAppStorage.removeAllAssociatedFriendsOfUser(userId);
	}

	/*
	 * получить список всех друзей (по типу User)
	 */
	@Override
	public List<User> getAllFriendsOfUser(Long userId) {
		String errorMessage = "Невозможно получить список друзей пользователя";
		exceptionsChecker.checkUserNotFoundException(userId, errorMessage);
		return friendAppStorage.getAllFriendsOfUser(userId);
	}

	@Override
	public List<User> getCommonFriendsOfUsers(Long userOneId, Long userTwoId) throws UsersAreNotFriendsException {
		String errorMessage = "Невозможно получить список друзей пользователя";
		exceptionsChecker.checkUserNotFoundException(userOneId, errorMessage);
		exceptionsChecker.checkUserNotFoundException(userTwoId, errorMessage);
		exceptionsChecker.checkUsersAreNotFriendsException(userOneId, userTwoId, errorMessage);
		return friendAppStorage.getCommonFriends(userOneId, userTwoId);
	}
}
