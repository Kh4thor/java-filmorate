package ru.yandex.practicum.filmorate.exceptions.genreExceptions;

public class GenreIsOutOfRangeException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	private String errorMessage;
	private int genreId;

	public GenreIsOutOfRangeException(int genreId, String errorMessage) {
		super("Значение genreId=" + genreId + ". Допустимый диапазон от 1 до 6 включительно.");
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
