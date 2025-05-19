package ru.yandex.practicum.filmorate.exceptions;

public class FriendServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Long id;

	public long getId() {
		return id;
	}

	public FriendServiceException(Long id) {
		this.id = id;
	}

}
