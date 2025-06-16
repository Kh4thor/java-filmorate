package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.yandex.practicum.filmorate.model.user.User;

@Getter
@Setter
@AllArgsConstructor
public class FriendshipDAO {

	private User userOne;
	private User userTwo;
	private FriendshipStatus friendshipStatus;

	public void addRequest(User userOne, User userTwo) {

	}

}
