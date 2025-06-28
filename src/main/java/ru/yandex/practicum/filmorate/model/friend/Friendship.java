package ru.yandex.practicum.filmorate.model.friend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.yandex.practicum.filmorate.model.user.User;

@Getter
@Setter
@AllArgsConstructor
public class Friendship {

	private User userOne;
	private User userTwo;
	private FriendshipStatus friendshipStatus;

	public void createFriendship(User userOne, User userTwo) {
		this.userOne = userOne;
		this.userTwo = userTwo;
		this.friendshipStatus = FriendshipStatus.NOT_FRIEND;
	}

}
