package ru.yandex.practicum.filmorate.model.mpa;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Mpa {

	private int id;
	private String name;
	private String description;

	@Override
	public int hashCode() {
		return Objects.hash(description, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mpa other = (Mpa) obj;
		return Objects.equals(description, other.description) && id == other.id && Objects.equals(name, other.name);
	}

}
