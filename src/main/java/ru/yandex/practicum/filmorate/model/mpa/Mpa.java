package ru.yandex.practicum.filmorate.model.mpa;

public class Mpa {

	private Integer id;
	private String name;

	public Mpa() {
	}

	public Mpa(Integer id) {
		this.id = id;
	}

	public Mpa(String name) {
		this.name = name;
	}

	public Mpa(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}