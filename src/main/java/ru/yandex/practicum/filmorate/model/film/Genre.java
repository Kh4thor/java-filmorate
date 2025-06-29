package ru.yandex.practicum.filmorate.model.film;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Genre {
	private int id;

	public Genre() {
	};

	public Genre(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}