package ru.yandex.practicum.filmorate.model.film;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Genre {
	private int id;
	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}