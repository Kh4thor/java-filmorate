package ru.yandex.practicum.filmorate.model.genre;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Genre {

	private int id;
	private String name;

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		return id == other.id && Objects.equals(name, other.name);
	}

}
