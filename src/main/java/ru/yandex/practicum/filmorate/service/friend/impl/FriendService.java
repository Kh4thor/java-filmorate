package ru.yandex.practicum.filmorate.service.friend.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.impl.ExceptionChecker;
import ru.yandex.practicum.filmorate.exceptions.friendExceptions.UsersAreNotFriendsException;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.service.friend.FriendAppService;
import ru.yandex.practicum.filmorate.storage.friend.FriendAppStorage;
import ru.yandex.practicum.filmorate.storage.user.UserAppStorage;

@Service
public class FriendService implements FriendAppService {

	private final UserAppStorage<User> userAppStorage;
	private final FriendAppStorage friendAppStorage;
	private final ExceptionChecker exceptionsChecker;

	public FriendService(UserAppStorage<User> usersAppStorage, FriendAppStorage friendAppStorage,
			ExceptionChecker exceptionsChecker) {
		this.friendAppStorage = friendAppStorage;
		this.userAppStorage = usersAppStorage;
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
		friendAppStorage.associateUsersAsFriends(userOneId, userTwoId);
		return true;
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
		friendAppStorage.disassociateUserAsFriends(userOneId, userTwoId);
		return true;
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
		List<Long> friendsIdList = friendAppStorage.getIdListOfAssociatedFriends(userId);

		return friendsIdList.stream().map(id -> userAppStorage.getUser(id)).toList();
	}

	@Override
	public List<User> getCommonFriendsOfUsers(Long userOneId, Long userTwoId) throws UsersAreNotFriendsException {
		String errorMessage = "Невозможно получить список друзей пользователя";
		exceptionsChecker.checkUserNotFoundException(userOneId, errorMessage);
		exceptionsChecker.checkUserNotFoundException(userTwoId, errorMessage);
		exceptionsChecker.checkUsersAreNotFriendsException(userOneId, userTwoId, errorMessage);
		List<Long> commonFriendsIdList = friendAppStorage.getIdListOfCommonFriends(userOneId, userTwoId);

		return commonFriendsIdList.stream().map(id -> userAppStorage.getUser(id)).toList();
	}
}
