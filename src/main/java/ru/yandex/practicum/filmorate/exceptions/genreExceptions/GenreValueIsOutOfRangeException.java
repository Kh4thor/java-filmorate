package ru.yandex.practicum.filmorate.exceptions.genreExceptions;

public class GenreValueIsOutOfRangeException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	private String errorMessage;
	private int genreId;

	public GenreValueIsOutOfRangeException(int genreId, String errorMessage) {
		super("Значение id-жанра должно быть больше нуля. Заданное значение id=:" + genreId);
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
