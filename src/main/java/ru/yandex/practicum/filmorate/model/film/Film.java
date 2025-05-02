package ru.yandex.practicum.filmorate.model.film;

import lombok.Data;

import java.time.LocalDate;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

/**
 * Film.
 */

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Film implements Cloneable {

	// описание фильма
	@Size(max = 200, message = "Максимальная длина описания — 200 символов")
	private String description;

	// профдолжительность фильма
	@Positive(message = "Значение поля duration должно быть положительным числом")
	@NotNull(message = "Поле duration не может быть null")
	private Long duration;

	// id фильма
	private Long id;

	// дата выхода фильма
	@Past(message = "Значение поля releaseDate не может быть текущей или будующей датой")
	@ReleaseDateValidation
	@NotNull(message = "Поле releaseDate не может быть null")
	private LocalDate releaseDate;

	// заголовок фильма
	@NotBlank(message = "Поле title не может быть пустым")
	private String title;

	@Override
	public Film clone() throws CloneNotSupportedException {
		return (Film) super.clone();
	}
}
