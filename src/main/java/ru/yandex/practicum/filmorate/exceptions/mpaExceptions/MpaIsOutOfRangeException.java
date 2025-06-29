package ru.yandex.practicum.filmorate.exceptions.mpaExceptions;

public class MpaIsOutOfRangeException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	private String errorMessage;
	private int mpaId;

	public MpaIsOutOfRangeException(int mpaId, String errorMessage) {
		super("Значение genreId=" + mpaId + ". Допустимый диапазон от 1 до 5 включительно.");
		this.mpaId = mpaId;
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public int getMpaId() {
		return mpaId;
	}

}
