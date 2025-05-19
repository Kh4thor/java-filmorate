package ru.yandex.practicum.filmorate.service.friend.impl;

import java.util.List;
import java.util.Optional;

import ru.yandex.practicum.filmorate.exceptions.FriendServiceException;
import ru.yandex.practicum.filmorate.exceptions.UserStorageException;
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

	private void checkUserServiceExceptions(long userOneId, long userTwoId)
			throws FriendServiceException, UserStorageException {
		if (!appStorage.isEntityExist(userOneId)) {
			throw new UserStorageException("Пользователя c id=" + userOneId + " нет в списке");
		}
		if (!appStorage.isEntityExist(userTwoId)) {
			throw new UserStorageException("Пользователя c id=" + userTwoId + " нет в списке");
		}
	}

	/*
	 * объеденить пользователей в друзей
	 */
	@Override
	public boolean associateUsersAsFriends(long userOneId, long userTwoId)
			throws FriendServiceException, UserStorageException {
		if (associatedEntities.isEntitiesAssociated(userOneId, userTwoId)) {
			throw new FriendServiceException("Пользователи уже друзья");
		}
		checkUserServiceExceptions(userOneId, userTwoId);
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
	public void disassociateUsersAsFriends(long userOneId, long userTwoId)
			throws FriendServiceException, UserStorageException {
		checkUserServiceExceptions(userOneId, userTwoId);
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
		return associatedEntities.getAllAssociatedEntitiesById(userId);
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
	public List<User> getCommonFriendsOfUsers(long userOneId, long userTwoId)
			throws FriendServiceException, UserStorageException {
		checkUserServiceExceptions(userOneId, userTwoId);
		associatedEntities.getCommonEntitiesById(userOneId, userTwoId);
		return null;
	}
}
