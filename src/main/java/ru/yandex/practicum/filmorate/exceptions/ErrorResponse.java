package ru.yandex.practicum.filmorate.exceptions;

public class ErrorResponse {
	private final String errorMessage;
	private final String description;

	public ErrorResponse(String errorMessage, String description) {
		this.errorMessage = errorMessage;
		this.description = description;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getDescription() {
		return description;
	}
}