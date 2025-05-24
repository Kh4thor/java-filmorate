package ru.yandex.practicum.filmorate.service.friend.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.filmorate.exceptions.userExceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.userExceptions.UsersAreAllreadyFriendsException;
import ru.yandex.practicum.filmorate.exceptions.userExceptions.UsersAreNotFriendsException;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.service.friend.FriendsAppService;
import ru.yandex.practicum.filmorate.storage.friend.FriendsAppStorage;
import ru.yandex.practicum.filmorate.storage.user.UserAppStorage;

@Service
public class FriendService implements FriendsAppService {

	private final UserAppStorage<User> userAppStorage;
	private final FriendsAppStorage<User> friendsAppStorage;

	public FriendService(UserAppStorage<User> userAppStorage, FriendsAppStorage<User> friendsAppStorage) {
		this.friendsAppStorage = friendsAppStorage;
		this.userAppStorage = userAppStorage;
	}

	/*
	 * объеденить пользователей в друзей
	 */
	@Override
	public boolean associateUsersAsFriends(long userOneId, long userTwoId) {
		String error = "Невозможно добавить пользователя в друзья";
		checkUsersAreAllredayFriendsException(userOneId, userTwoId, error);
		checkUserNotFoundException(userOneId, userTwoId, error);
		friendsAppStorage.associateEntitiesById(userOneId, userTwoId);
		return true;
	}

	/*
	 * проверка пользователей, являются ли они друзьями (by user's id)
	 */
	@Override
	public boolean isUsersAreFriends(long userOneId, long userTwoId) {
		return friendsAppStorage.isEntitiesAssociated(userOneId, userTwoId);
	}

	/*
	 * убрать пользователей из списка друзей друг друга
	 */
	@Override
	public boolean disassociateUsersAsFriends(long userOneId, long userTwoId) {
		String error = "Невозможно удалить пользователя из друзей";
		checkUserNotFoundException(userOneId, userTwoId, error);
		checkUsersAreNotFriendsException(userOneId, userTwoId, error);
		friendsAppStorage.disassociateEntitiesById(userOneId, userTwoId);
		return true;
	}

	/*
	 * очистить список друзей пользователя
	 */
	@Override
	public void disassociateAllFriendsOfUser(long userId) {
		friendsAppStorage.removeAllAssociatedEntitiesById(userId);
	}

	/*
	 * получить список всех друзей (по типу User)
	 */
	@Override
	public List<User> getAllFriendsOfUser(long userId) {
		List<Long> friendsIdList = friendsAppStorage.getIdListOfAssociatedEntities(userId);
		// для каждого id из списка friendsIdList вытаскиваем пользователя из userStorage
		return friendsIdList.stream().map(id -> userAppStorage.get(id)).toList();
	}

	@Override
	public List<User> getCommonFriendsOfUsers(long userOneId, long userTwoId) throws UsersAreNotFriendsException {
		List<Long> commonFriendsIdList = friendsAppStorage.geIdListOfCommonEntities(userOneId, userTwoId);
		// для каждого id из списка friendsIdList вытаскиваем пользоватедя из userStorage
		return commonFriendsIdList.stream().map(id -> userAppStorage.get(id)).toList();
	}

	/*
	 * находятся ли пользователи userOne и userTwo в хранилище
	 */
	private void checkUserNotFoundException(long userOneId, long userTwoId, String error) throws UserNotFoundException {
		checkUserNotFoundException(userOneId, error);
		checkUserNotFoundException(userTwoId, error);
	}

	/*
	 * являются ли пользователи userOne и userTwo друзьями
	 */
	private void checkUsersAreAllredayFriendsException(long userOneId, long userTwoId, String error) {
		if (friendsAppStorage.isEntitiesAssociated(userOneId, userTwoId)) {
			throw new UsersAreAllreadyFriendsException(userOneId, userTwoId, error);
		}
	}

	private void checkUsersAreNotFriendsException(long userOneId, long userTwoId, String error)
			throws UsersAreNotFriendsException {
		if (!isUsersAreFriends(userOneId, userTwoId)) {
			throw new UsersAreNotFriendsException(userOneId, userTwoId, error);
		}
	}

	/*
	 * находится ли пользователь user в хранилище
	 */
	private void checkUserNotFoundException(long userId, String error) throws UserNotFoundException {
		if (!userAppStorage.isEntityExist(userId)) {
			throw new UserNotFoundException(userId, error);
		}
	}
}
