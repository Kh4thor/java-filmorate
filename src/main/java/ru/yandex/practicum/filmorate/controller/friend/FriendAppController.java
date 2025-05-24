package ru.yandex.practicum.filmorate.controller.friend;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import ru.yandex.practicum.filmorate.model.user.User;

public interface FriendAppController {

	/*
	 * добавить пользователя в друзья
	 */
	ResponseEntity<String> addFriend(@PathVariable long id, @PathVariable long friendsId);

	/*
	 * удалить пользователя из друзей
	 */
	ResponseEntity<String> deleteFriend(@PathVariable long id, @PathVariable long friendsId);

	/*
	 * получить друзей пользователя
	 */
	List<User> getListOfFriends(@PathVariable long id);

	/*
	 * получить список общих друзей
	 */
	List<User> getCommonFriends(@PathVariable long id, @PathVariable long otherId);

}