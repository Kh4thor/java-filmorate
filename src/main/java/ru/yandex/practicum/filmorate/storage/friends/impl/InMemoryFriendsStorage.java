package ru.yandex.practicum.filmorate.storage.friends.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import ru.yandex.practicum.filmorate.storage.friends.FriendsAppStorage;

@Component
public class InMemoryFriendsStorage implements FriendsAppStorage {

	Map<Long, List<Long>> friendsIdMap = new HashMap<>();

	/*
	 * связать пользователей в качестве друзей
	 */
	@Override
	public boolean associateUsersAsFriends(Long userOneId, Long userTwoId) {
		List<Long> friendsIdListOfUserOne = friendsIdMap.get(userOneId);
		List<Long> friendsIdListOfUserTwo = friendsIdMap.get(userTwoId);
		friendsIdListOfUserOne.add(userTwoId);
		friendsIdListOfUserTwo.add(userOneId);
		return true;
	}

	/*
	 * отвязать пользователей в качестве друзей
	 */
	@Override
	public boolean disassociateUserAsFriends(Long userOneId, Long userTwoId) {
		List<Long> friendsIdListOfUserOne = friendsIdMap.get(userOneId);
		friendsIdListOfUserOne.remove(userTwoId);

		List<Long> friendsIdListOfUserTwo = friendsIdMap.get(userTwoId);
		friendsIdListOfUserTwo.remove(userOneId);

		return true;
	}

	/*
	 * проверка пользователей на дружбу
	 */
	@Override
	public boolean isUsersAssociatedAsFriends(Long userOneId, Long userTwoId) {
		List<Long> friendsIdListOfUserOne = friendsIdMap.get(userOneId);
		List<Long> friendsIdListOfUserTwo = friendsIdMap.get(userTwoId);
		if (friendsIdListOfUserOne.contains(userTwoId) && friendsIdListOfUserTwo.contains(userOneId)) {
			return true;
		}
		return false;
	}

	/*
	 * получить id-список связанных друзей пользователя
	 */
	@Override
	public List<Long> getIdListOfAssociatedFriends(Long userId) {
		return friendsIdMap.get(userId);
	}

	/*
	 * удалить у пользователя всех привязанных друзей
	 */
	@Override
	public void removeAllAssociatedFriendsOfUser(Long userId) {
		friendsIdMap.remove(userId);

	}

	/*
	 * получить id-список общих друзей двух пользователей
	 */
	@Override
	public List<Long> geIdListOfCommonFriends(Long userOneId, Long userTwoId) {
		List<Long> listIdOfCommonEntities = new ArrayList<>();
		List<Long> friendsIdListOfUserOne = friendsIdMap.get(userOneId);
		List<Long> friendsIdListOfUserTwo = friendsIdMap.get(userTwoId);

		for (Long friendId : friendsIdListOfUserOne) {
			if (friendsIdListOfUserTwo.contains(friendId)) {
				listIdOfCommonEntities.add(friendId);
			}
		}
		return listIdOfCommonEntities;
	}

	/*
	 * добавить пользователя в хранилище
	 */
	@Override
	public void addUser(Long id) {
		friendsIdMap.put(id, new ArrayList<>());
	}

	/*
	 * удалить пользователя из хранилища
	 */
	@Override
	public void deleteUser(Long userId) {
		List<Long> friendsIdListOfUser = friendsIdMap.get(userId);

		// удалить id пользователя из списка друзей других пользователей
		for (Long friendsId : friendsIdListOfUser) {
			List<Long> friendsIdListOfFriend = friendsIdMap.get(friendsId);
			friendsIdListOfFriend.remove(userId);
		}
		// удалить пользователя из хранилища
		friendsIdMap.remove(userId);
	}

	/*
	 * удалить всех пользователей из хранилища
	 */
	@Override
	public void clearStorage() {
		friendsIdMap.clear();
	}
}
