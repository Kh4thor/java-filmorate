package ru.yandex.practicum.filmorate.storage.friend.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.storage.film.FilmAppStorage;
import ru.yandex.practicum.filmorate.storage.friend.FriendsAppStorage;

@Component
public class InMemoryFriendsStorage implements FriendsAppStorage<User> {

	Map<Long, List<Long>> friendsIdMap = new HashMap<>();

	FilmAppStorage<User> appStorage;

	public InMemoryFriendsStorage(FilmAppStorage<User> appStorage) {
		this.appStorage = appStorage;
	}

	@Override
	public boolean associateEntitiesById(long userOneId, long userTwoId) {
		friendsIdMap.get(userOneId);
		return true;
	}

	@Override
	public boolean disassociateEntitiesById(long userOneId, long userTwoId) {
		List<Long> userOneIdFriends = friendsIdMap.get(userOneId);
		userOneIdFriends.remove(userTwoId);

		List<Long> userTwoIdFriends = friendsIdMap.get(userTwoId);
		userTwoIdFriends.remove(userOneId);

		return true;
	}

	/*
	 * проверка
	 */
	@Override
	public boolean isEntitiesAssociated(long userOneId, long userTwoId) {
		List<Long> userOneIdFriends = friendsIdMap.get(userOneId);
		List<Long> userTwoIdFriends = friendsIdMap.get(userTwoId);
		if (userOneIdFriends.contains(userTwoId) && userTwoIdFriends.contains(userOneId)) {
			return true;
		}
		return false;
	}

	/*
	 * получить список друзей (по типу User)
	 */
	@Override
	public List<User> getAllAssociatedEntitiesById(long userId) {

		List<User> friendsList = new ArrayList<>();

		for (long friendId : friendsIdMap.get(userId)) {
			User friend = appStorage.get(friendId);
			friendsList.add(friend);
		}
		return friendsList;
	}

	@Override
	public Optional<User> getAssociatedEntity(long userOneId, long userTwoId) {
		User friend = appStorage.get(userTwoId);
		return Optional.of(friend);
	}

	@Override
	public void removeAllAssociatedEntitiesById(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getCommonEntitiesById(long entityOneId, long entityTwoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEntityToStorage(long id) {
		friendsIdMap.put(id, new ArrayList<>());
	}

	@Override
	public void deleteEntityFromStorage(long userId) {
		List<Long> friendsIdListOfUser = friendsIdMap.get(userId);

		// удалить id пользователя из списка друзей других пользователей
		for (long friendsId : friendsIdListOfUser) {
			List<Long> friendsIdListOfFriend = friendsIdMap.get(friendsId);
			friendsIdListOfFriend.remove(userId);
		}
		// удалить пользователя из хранилища
		friendsIdMap.remove(userId);
	}

	@Override
	public void clearStorage() {
		friendsIdMap.clear();
	}

}
