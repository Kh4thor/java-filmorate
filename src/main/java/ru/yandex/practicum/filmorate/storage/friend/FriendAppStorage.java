package ru.yandex.practicum.filmorate.storage.friend;

import java.util.List;

public interface FriendAppStorage {

	/*
	 * добавить пользователя в хранилище
	 */
	void addUser(Long id);

	/*
	 * связать пользователей в качестве друзей
	 */
	boolean associateUsersAsFriends(Long userOneId, Long userTwoId);

	/*
	 * отвязать пользователей в качестве друзей
	 */
	boolean disassociateUserAsFriends(Long userOneId, Long userTwoId);

	/*
	 * проверка пользователей на дружбу
	 */
	boolean isUsersAssociatedAsFriends(Long userOneId, Long userTwoId);

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
	List<Long> getIdListOfCommonFriends(Long userOneId, Long userTwoId);

	/*
	 * отправить запрос на дружбу
	 */
	void sendRequestToUserForFriendship(Long userOneId, Long userTwoId);

}