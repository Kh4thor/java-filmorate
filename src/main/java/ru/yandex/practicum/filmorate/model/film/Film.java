package ru.yandex.practicum.filmorate.model.film;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*
 * Film.
 */
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Film implements Cloneable {

	// id фильма
	private Long id;

	// название фильма
	@NotBlank(message = "Поле name не может быть пустым")
	private String name;

	// описание фильма
	@Size(max = 200, message = "Максимальная длина описания — 200 символов")
	private String description;

	// дата выхода фильма
	@Past(message = "Значение поля releaseDate не может быть текущей или будующей датой")
	@ReleaseDateValidation
	@NotNull(message = "Поле releaseDate не может быть null")
	private LocalDate releaseDate;

	// профдолжительность фильма
	@Positive(message = "Значение поля duration должно быть положительным числом")
	@NotNull(message = "Поле duration не может быть null")
	private Long duration;

	@Override
	public int hashCode() {
		return Objects.hash(description, duration, name, releaseDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		Film other = (Film) obj;
		return Objects.equals(description, other.description) && Objects.equals(duration, other.duration)
				&& Objects.equals(name, other.name) && Objects.equals(releaseDate, other.releaseDate);
	}

	@Override
	public Film clone() throws CloneNotSupportedException {
		return (Film) super.clone();
	}
}
