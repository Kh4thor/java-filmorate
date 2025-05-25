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
	 * связать сущности
	 */
	@Override
	public boolean associateEntitiesById(long userOneId, long userTwoId) {
		List<Long> friendsIdListOfUserOne = friendsIdMap.get(userOneId);
		List<Long> friendsIdListOfUserTwo = friendsIdMap.get(userTwoId);
		friendsIdListOfUserOne.add(userTwoId);
		friendsIdListOfUserTwo.add(userOneId);
		return true;
	}

	/*
	 * отвязать сущности
	 */
	@Override
	public boolean disassociateEntitiesById(long userOneId, long userTwoId) {
		List<Long> friendsIdListOfUserOne = friendsIdMap.get(userOneId);
		friendsIdListOfUserOne.remove(userTwoId);

		List<Long> friendsIdListOfUserTwo = friendsIdMap.get(userTwoId);
		friendsIdListOfUserTwo.remove(userOneId);

		return true;
	}

	/*
	 * проверка сущностей на связь
	 */
	@Override
	public boolean isEntitiesAssociated(long userOneId, long userTwoId) {
		List<Long> friendsIdListOfUserOne = friendsIdMap.get(userOneId);
		List<Long> friendsIdListOfUserTwo = friendsIdMap.get(userTwoId);
		if (friendsIdListOfUserOne.contains(userTwoId) && friendsIdListOfUserTwo.contains(userOneId)) {
			return true;
		}
		return false;
	}

	/*
	 * получить id-список связанных сущностей
	 */
	@Override
	public List<Long> getIdListOfAssociatedEntities(long userId) {
		return friendsIdMap.get(userId);
	}

	/*
	 * удалить у сущности все привязанные сущности
	 */
	@Override
	public void removeAllAssociatedEntitiesById(long userId) {
		friendsIdMap.remove(userId);

	}

	/*
	 * получить id-список общих связанных сущностей
	 */
	@Override
	public List<Long> geIdListOfCommonEntities(long userOneId, long userTwoId) {
		List<Long> listIdOfCommonEntities = new ArrayList<>();
		List<Long> friendsIdListOfUserOne = friendsIdMap.get(userOneId);
		List<Long> friendsIdListOfUserTwo = friendsIdMap.get(userTwoId);

		for (long friendId : friendsIdListOfUserOne) {
			if (friendsIdListOfUserTwo.contains(friendId)) {
				listIdOfCommonEntities.add(friendId);
			}
		}
		return listIdOfCommonEntities;
	}

	/*
	 * добавить сущность в хранилище
	 */
	@Override
	public void addEntityToStorage(long id) {
		friendsIdMap.put(id, new ArrayList<>());
	}

	/*
	 * удалить сущность из хранилища
	 */
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

	/*
	 * удалить все сущности из хранилища
	 */
	@Override
	public void clearStorage() {
		friendsIdMap.clear();
	}
}
