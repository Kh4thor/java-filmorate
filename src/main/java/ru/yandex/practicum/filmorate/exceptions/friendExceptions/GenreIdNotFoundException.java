package ru.yandex.practicum.filmorate.exceptions.friendExceptions;

import java.util.NoSuchElementException;

public class GenreIdNotFoundException extends NoSuchElementException {

	private static final long serialVersionUID = 1L;

	private int genreId;
	private String errorMessage;

	public GenreIdNotFoundException(int genreId, String errorMessage) {
		super("id=" + genreId + " в таблице genres не найден");
		this.genreId = genreId;
		this.errorMessage = errorMessage;
	}

	public int getGenreId() {
		return genreId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
