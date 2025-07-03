package ru.yandex.practicum.filmorate.exceptions.mpaExceptions;

import java.util.NoSuchElementException;

public class MpaNotFoundException extends NoSuchElementException {

	private static final long serialVersionUID = 1L;

	private int mpaId;
	private String errorMessage;

	public MpaNotFoundException(int mpaId, String errorMessage) {
		super("id=" + mpaId + " в базе mpa не найден");
		this.mpaId = mpaId;
		this.errorMessage = errorMessage;
	}

	public int getMpaId() {
		return mpaId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
