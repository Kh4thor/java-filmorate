package ru.yandex.practicum.filmorate.storage.friends;

import java.util.List;

public interface FriendsAppStorage {

	/*
	 * добавить пользователя в хранилище
	 */
	void addUser(long id);

	/*
	 * связать пользователей в качестве друзей
	 */
	boolean associateUsersAsFriends(long entityOneId, long entityTwoId);

	/*
	 * отвязать пользователей в качестве друзей
	 */
	boolean disassociateUserAsFriends(long entityOneId, long entityTwoId);

	/*
	 * проверка пользователей на дружбу
	 */
	boolean isUsersAssociatedAsFriends(long entityOneId, long entityTwoId);

	/*
	 * удалить у пользователя всех привязанных друзей
	 */
	void removeAllAssociatedFriendsOfUser(long userId);

	/*
	 * удалить пользователя из хранилища
	 */
	void deleteUser(long id);

	/*
	 * удалить всех пользователей из хранилища
	 */
	void clearStorage();

	/*
	 * получить id-список связанных друзей пользователя
	 */
	List<Long> getIdListOfAssociatedFriends(long userId);

	/*
	 * получить id-список общих друзей двух пользователей
	 */
	List<Long> geIdListOfCommonFriends(long entityOneId, long entityTwoId);
}