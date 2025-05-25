package ru.yandex.practicum.filmorate.storage.friends;

import java.util.List;

public interface FriendsAppStorage {

	/*
	 * добавить сущность в хранилище
	 */
	void addEntityToStorage(long id);

	/*
	 * связать сущности
	 */
	boolean associateEntitiesById(long entityOneId, long entityTwoId);

	/*
	 * отвязать сущности
	 */
	boolean disassociateEntitiesById(long entityOneId, long entityTwoId);

	/*
	 * проверка сущностей на связь
	 */
	boolean isEntitiesAssociated(long entityOneId, long entityTwoId);

	/*
	 * удалить у сущности все привязанные сущности
	 */
	void removeAllAssociatedEntitiesById(long userId);

	/*
	 * удалить сущность из хранилища
	 */
	void deleteEntityFromStorage(long id);

	/*
	 * удалить все сущности из хранилища
	 */
	void clearStorage();

	/*
	 * получить id-список связанных сущностей
	 */
	List<Long> getIdListOfAssociatedEntities(long userId);

	/*
	 * получить id-список общих связанных сущностей
	 */
	List<Long> geIdListOfCommonEntities(long entityOneId, long entityTwoId);
}