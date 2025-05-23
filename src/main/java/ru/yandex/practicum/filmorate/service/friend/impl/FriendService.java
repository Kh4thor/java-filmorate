package ru.yandex.practicum.filmorate.service.friend.impl;

import java.util.List;
import java.util.Optional;

import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.UsersAreAllreadyFriendsException;
import ru.yandex.practicum.filmorate.exceptions.UsersAreNotFriendsException;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.service.friend.FriendsAppService;
import ru.yandex.practicum.filmorate.storage.film.FilmAppStorage;
import ru.yandex.practicum.filmorate.storage.friend.FriendsAppStorage;

public class FriendService implements FriendsAppService {

	private final FilmAppStorage<User> appStorage;
	private final FriendsAppStorage<User> associatedEntities;

	public FriendService(FilmAppStorage<User> appStorage, FriendsAppStorage<User> associatedEntities) {
		this.appStorage = appStorage;
		this.associatedEntities = associatedEntities;
	}

	/*
	 * находятся ли пользователи userOne и userTwo в хранилище
	 */
	private void checkUserNotFoundException(long userOneId, long userTwoId) throws UserNotFoundException {
		checkUserNotFoundException(userOneId);
		checkUserNotFoundException(userTwoId);
	}

	/*
	 * находится ли пользователь user в хранилище
	 */
	private void checkUserNotFoundException(long userId) throws UserNotFoundException {
		if (!appStorage.isEntityExist(userId)) {
			throw new UserNotFoundException(userId);
		}
	}

	/*
	 * являются ли пользователи userOne и userTwo друзьями
	 */
	private void checkUsersAreAllredayFriendsException(long userOneId, long userTwoId)
			throws UsersAreAllreadyFriendsException {
		if (associatedEntities.isEntitiesAssociated(userOneId, userTwoId)) {
			throw new UsersAreAllreadyFriendsException(userOneId, userTwoId);
		}
	}

	/*
	 * объеденить пользователей в друзей
	 */
	@Override
	public boolean associateUsersAsFriends(long userOneId, long userTwoId)
			throws UsersAreAllreadyFriendsException, UserNotFoundException {
		checkUsersAreAllredayFriendsException(userOneId, userTwoId);
		checkUserNotFoundException(userOneId, userTwoId);
		associatedEntities.associateEntitiesById(userOneId, userTwoId);
		return true;
	}

	/*
	 * проверка пользователей, являются ли они друзьями (by user's id)
	 */
	@Override
	public boolean isUsersAreFriends(long userOneId, long userTwoId) {
		return associatedEntities.isEntitiesAssociated(userOneId, userTwoId);
	}

	/*
	 * убрать пользователей из списка друзей друг друга
	 */
	@Override
	public void disassociateUsersAsFriends(long userOneId, long userTwoId) throws UserNotFoundException {
		checkUserNotFoundException(userOneId, userTwoId);
		associatedEntities.disassociateEntitiesById(userOneId, userTwoId);
	}

	/*
	 * очистить список друзей пользователя
	 */
	@Override
	public void disassociateAllFriendsOfUser(long userId) {
		associatedEntities.removeAllAssociatedEntitiesById(userId);
	}

	/*
	 * получить список всех друзей (по типу User)
	 */
	@Override
	public List<User> getAllFriendsOfUserById(long userId) {
		List<Long> friendsIdList = associatedEntities.getListIdOfAssociatedEntities(userId);
		// для каждого id из списка friendsIdList вытаскиваем пользователя из userStorage
		return friendsIdList.stream().map(id -> appStorage.get(id)).toList();
	}

	/*
	 * получить друга (по типу User)
	 */
	@Override
	public Optional<User> getFriendOfUser(long userId, long friendId) {
		if (isUsersAreFriends(userId, userId)) {
			return associatedEntities.getAssociatedEntity(userId, friendId);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<User> getCommonFriendsOfUsers(long userOneId, long userTwoId) throws UsersAreNotFriendsException {
		if (isUsersAreFriends(userOneId, userTwoId)) {
			List<Long> commonFriendsIdList = associatedEntities.getListIdOfCommonEntitiesById(userOneId, userTwoId);
			// для каждого id из списка friendsIdList вытаскиваем пользоватедя из userStorage
			return commonFriendsIdList.stream().map(id -> appStorage.get(id)).toList();
		}
		throw new UsersAreNotFriendsException(userOneId, userTwoId);
	}
}
