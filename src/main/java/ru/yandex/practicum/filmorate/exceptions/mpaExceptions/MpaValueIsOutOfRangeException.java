package ru.yandex.practicum.filmorate.exceptions.mpaExceptions;

public class MpaValueIsOutOfRangeException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	private String errorMessage;
	private int mpaId;

	public MpaValueIsOutOfRangeException(int mpaId, String errorMessage) {
		super("Значение id-рейтинга=" + mpaId + ". Допустимый диапазон от 1 до 5 включительно.");
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
