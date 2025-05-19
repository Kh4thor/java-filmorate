package ru.yandex.practicum.filmorate.storage.friend;

import java.util.List;
import java.util.Optional;

public interface FriendsAppStorage<T> {

	/*
	 * добавить сущность в хранилище
	 */
	void addEntityToStorage(long id);

	/*
	 * связать сущности
	 */
	boolean associateEntitiesById(long entityOneId, long entityTwoId);

	/*
	 * развязать сущности
	 */
	boolean disassociateEntitiesById(long entityOneId, long entityTwoId);

	/*
	 * проверка сущностей на связь
	 */
	boolean isEntitiesAssociated(long entityOneId, long entityTwoId);

	/*
	 * получить все привязанные сущности к сущности
	 */
	List<T> getAllAssociatedEntitiesById(long entityId);

	/*
	 * получить привязанную сущность
	 */
	public Optional<T> getAssociatedEntity(long entityOneId, long entityTwoId);

	/*
	 * удалить у сущности все привязанные сущности
	 */
	void removeAllAssociatedEntitiesById(long userId);

	/*
	 * получить список общих сущностией
	 */
	List<T> getCommonEntitiesById(long entityOneId, long entityTwoId);

	/*
	 * удалить сущность из хранилища
	 */
	void deleteEntityFromStorage(long id);

	/*
	 * удалить все сущности из хранилища
	 */
	void clearStorage();
}