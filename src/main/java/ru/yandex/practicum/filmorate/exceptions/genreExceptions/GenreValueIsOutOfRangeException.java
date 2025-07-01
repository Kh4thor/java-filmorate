package ru.yandex.practicum.filmorate.exceptions.genreExceptions;

public class GenreValueIsOutOfRangeException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	private String errorMessage;
	private int genreId;

	public GenreValueIsOutOfRangeException(int genreId, String errorMessage) {
		super("Значение id-жанра=" + genreId + ". Допустимый диапазон от 1 до 6 включительно.");
		this.genreId = genreId;
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public int getMpaId() {
		return genreId;
	}

}
