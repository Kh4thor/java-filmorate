package ru.yandex.practicum.filmorate.exceptions.mpaExceptions;

public class MpaValueIsOutOfRangeException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	private String errorMessage;
	private int mpaId;

	public MpaValueIsOutOfRangeException(int mpaId, String errorMessage) {
		super("Значение id-рейтинга должно быть больше нуля. Заданное значение id=" + mpaId);
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
