package ru.yandex.practicum.filmorate.storage.friends;

import java.util.List;

public interface FriendsAppStorage {

	/*
	 * добавить пользователя в хранилище
	 */
	void addUser(Long id);

	/*
	 * связать пользователей в качестве друзей
	 */
	boolean associateUsersAsFriends(Long entityOneId, Long entityTwoId);

	/*
	 * отвязать пользователей в качестве друзей
	 */
	boolean disassociateUserAsFriends(Long entityOneId, Long entityTwoId);

	/*
	 * проверка пользователей на дружбу
	 */
	boolean isUsersAssociatedAsFriends(Long entityOneId, Long entityTwoId);

	/*
	 * удалить у пользователя всех привязанных друзей
	 */
	void removeAllAssociatedFriendsOfUser(Long userId);

	/*
	 * удалить пользователя из хранилища
	 */
	void deleteUser(Long id);

	/*
	 * удалить всех пользователей из хранилища
	 */
	void clearStorage();

	/*
	 * получить id-список связанных друзей пользователя
	 */
	List<Long> getIdListOfAssociatedFriends(Long userId);

	/*
	 * получить id-список общих друзей двух пользователей
	 */
	List<Long> geIdListOfCommonFriends(Long entityOneId, Long entityTwoId);
}