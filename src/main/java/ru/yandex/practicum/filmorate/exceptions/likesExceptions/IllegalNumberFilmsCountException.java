package ru.yandex.practicum.filmorate.exceptions.likesExceptions;

public class IllegalNumberFilmsCountException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	private int filmsCount;
	private String errorMessage;

	public IllegalNumberFilmsCountException(int filmsCount, String errorMessage) {
		super("Значение количества фильмов должно быть больше нуля.");
		this.filmsCount = filmsCount;
		this.errorMessage = errorMessage;
	}

	public int getFilmsCount() {
		return filmsCount;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
