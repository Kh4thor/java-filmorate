package ru.yandex.practicum.filmorate;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.storage.films.impl.InMemoryFilmsStorage;
import ru.yandex.practicum.filmorate.storage.likes.impl.InMemoryLikesStorage;
import ru.yandex.practicum.filmorate.storage.users.impl.InMemoryUsersStorage;

@SpringBootApplication
public class FilmorateApplication {
	public static void main(String[] args) {
		SpringApplication.run(FilmorateApplication.class, args);
		Test test = new Test();
		test.startTest();
	}

}

class Test {

	public void startTest() {

		User user1 = User.builder().id(1L).name("user").login("login").email("email@email.ru")
				.birthday(LocalDate.of(1986, 03, 01)).build();
		User user2 = User.builder().id(2L).name("user").login("login").email("email@email.ru")
				.birthday(LocalDate.of(1986, 03, 01)).build();
		User user3 = User.builder().id(3L).name("user").login("login").email("email@email.ru")
				.birthday(LocalDate.of(1986, 03, 01)).build();
		User user4 = User.builder().id(4L).name("user").login("login").email("email@email.ru")
				.birthday(LocalDate.of(1986, 03, 01)).build();
		User user5 = User.builder().id(5L).name("user").login("login").email("email@email.ru")
				.birthday(LocalDate.of(1986, 03, 01)).build();

		InMemoryUsersStorage userStorage = new InMemoryUsersStorage();
		userStorage.addUser(user1);
		userStorage.addUser(user2);
		userStorage.addUser(user3);
		userStorage.addUser(user4);
		userStorage.addUser(user5);

		Film film1 = Film.builder().id(1L).name("film").description("description").releaseDate(LocalDate.now())
				.duration(120L).build();
		Film film2 = Film.builder().id(2L).name("film").description("description").releaseDate(LocalDate.now())
				.duration(120L).build();
		Film film3 = Film.builder().id(3L).name("film").description("description").releaseDate(LocalDate.now())
				.duration(120L).build();
		Film film4 = Film.builder().id(4L).name("film").description("description").releaseDate(LocalDate.now())
				.duration(120L).build();
		Film film5 = Film.builder().id(5L).name("film").description("description").releaseDate(LocalDate.now())
				.duration(120L).build();

		InMemoryFilmsStorage filmStorage = new InMemoryFilmsStorage();
		filmStorage.addFilm(film1);
		filmStorage.addFilm(film2);
		filmStorage.addFilm(film3);
		filmStorage.addFilm(film4);
		filmStorage.addFilm(film5);

		InMemoryLikesStorage likesStorage = new InMemoryLikesStorage();
		likesStorage.addFilm(film1);
		likesStorage.addFilm(film2);
		likesStorage.addFilm(film3);
		likesStorage.addFilm(film4);
		likesStorage.addFilm(film5);

		likesStorage.setLike(5L, 1L);
		likesStorage.setLike(5L, 2L);
		likesStorage.setLike(5L, 3L);

		likesStorage.setLike(4L, 1L);
		likesStorage.setLike(4L, 2L);

		likesStorage.setLike(3L, 1L);

//		List<Film> listFilm = new LinkedList<>();
//		List<Long> listID = likesStorage.getIdListOfFilmsIdByRate(0);

//		List<Long> listId = likesStorage.getRepo().values().stream().flatMap(list -> list.stream()).toList();
//		likesStorage.getRepo().values().forEach(e -> System.out.println(e));
//		listId.forEach(e -> System.out.println(e));

		likesStorage.getIdListOfFilmsIdByRate(4).forEach(e -> System.out.println(e));

	}
}
