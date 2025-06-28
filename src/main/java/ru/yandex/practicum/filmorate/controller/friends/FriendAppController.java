package ru.yandex.practicum.filmorate.controller.friends;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import ru.yandex.practicum.filmorate.model.user.User;

public interface FriendAppController {

	/*
	 * добавить пользователя в друзья
	 */
	void addFriend(@PathVariable Long id, @PathVariable Long friendsId);

	/*
	 * удалить пользователя из друзей
	 */
	void deleteFriend(@PathVariable Long id, @PathVariable Long friendsId);

	/*
	 * получить друзей пользователя
	 */
	List<User> getListOfFriends(@PathVariable Long id);

	/*
	 * получить список общих друзей
	 */
	List<User> getCommonFriends(@PathVariable Long id, @PathVariable Long otherId);

}