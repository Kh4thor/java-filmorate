package ru.yandex.practicum.filmorate.mvc.storage.film.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.genre.Genre;
import ru.yandex.practicum.filmorate.model.mpa.Mpa;
import ru.yandex.practicum.filmorate.mvc.storage.film.FilmAppStorage;
import ru.yandex.practicum.filmorate.mvc.storage.mpa.MpaAppStorage;
import ru.yandex.practicum.filmorate.utills.mappers.FilmRowMapper;

@Repository("dbFilmStorage")
public class DbFilmStorage implements FilmAppStorage<Film> {

	private final JdbcTemplate jdbcTemplate;
	private final MpaAppStorage mpaAppStorage;

	public DbFilmStorage(JdbcTemplate jdbcTemplate, MpaAppStorage mpaAppStorage) {
		this.jdbcTemplate = jdbcTemplate;
		this.mpaAppStorage = mpaAppStorage;
	}

	/*
	 * добавть фильм
	 */
	@Override
	public Film addFilm(Film film) {
		// добавление фильма в таблицу films

		Integer mpaId = null;
		String sqlFilm = "INSERT INTO films (id, name, description, release, duration, mpa) VALUES (?, ?, ?, ?, ?, ?)";
		if (film.getMpa() != null) {
			mpaId = film.getMpa().getId();
		}
		jdbcTemplate.update(sqlFilm, film.getId(), film.getName(), film.getDescription(), film.getReleaseDate(),
				film.getDuration(), mpaId);
		addGenresOfFilm(film);
		return getFilm(film.getId());
	}

	/*
	 * добавление жанров фильма в промежуточную таблицу films_genres
	 */
	private void addGenresOfFilm(Film film) {
		String sqlGenre = "INSERT INTO films_genres (film_id, genre_id) VALUES (?,?)";
		List<Genre> genreIdList = film.getGenres();
		// заполнение таблицы films_genres для каждого значения id-жанра
		for (Genre element : genreIdList) {
			int genreId = element.getId();
			jdbcTemplate.update(sqlGenre, film.getId(), genreId);
		}
	}

	/*
	 * обновить фильм
	 */
	@Override
	public Film updateFilm(Film film) {
		String sql = "UPDATE films SET name=?, description=?, release=?, duration=?, mpa=? WHERE id=?";
		Integer mpaId = null;
		if (film.getMpa() != null) {
			mpaId = film.getMpa().getId();
		}
		jdbcTemplate.update(sql, film.getName(), film.getDescription(), film.getReleaseDate(), film.getDuration(),
				mpaId, film.getId());

		return getFilm(film.getId());
	}

	/*
	 * очистить хранилище
	 */
	@Override
	public void clear() {
		jdbcTemplate.update("TRUNCATE TABLE films");
	}

	/*
	 * проверить хранилище на наличие ключа-id фильма
	 */
	@Override
	public boolean isFilmExist(Long filmId) {
		String sql = "SELECT id FROM films WHERE id=? ";
		List<Long> filmIdList = jdbcTemplate.queryForList(sql, Long.class, filmId);
		return !filmIdList.isEmpty();
	}

	/*
	 * получить фильм из хранилища
	 */
	@Override
	public Film getFilm(Long filmId) {
		// получение фильма без поля genres
		String sqlFilm = "SELECT * FROM films WHERE id=? GROUP BY id";
		Film film = jdbcTemplate.queryForObject(sqlFilm, new FilmRowMapper(), filmId);
		// получение id-списка жанров полученного фильма
		String sqlGenreList = "SELECT genre_id FROM films_genres WHERE film_id=?";
		List<Genre> genreList = new ArrayList<>();
		List<Integer> genreIdList = jdbcTemplate.queryForList(sqlGenreList, Integer.class, filmId);
		// заполнение списка жанров <Genre> genres значениями из таблицы
		for (int i = 0; i < genreIdList.size(); i++) {
			int genreId = genreIdList.get(i);
			// присвоить имя для genre по id-genre
			String sqlNameGenre = "SELECT name FROM genres WHERE id=?";
			String nameGenre = jdbcTemplate.queryForObject(sqlNameGenre, String.class, genreId);
			Genre genre = new Genre(genreId);
			genre.setName(nameGenre);
			genreList.add(genre);
		}
		// присвоить mpa для фильма
		if (film.getMpa() != null) {
			Integer mpaId = film.getMpa().getId();
			// присвоить имя для mpa по id-mpa
			Mpa mpa = mpaAppStorage.getMpa(mpaId);
			film.setMpa(mpa);
		} else {
			film.setMpa(null);
		}
		// добоваление полученного id-списка жанров к полученному фильму
		film.setGenres(genreList);
		return film;
	}

	/*
	 * удалить фильм из хранилища
	 */
	@Override
	@Transactional
	public Film removeFilm(Long filmId) {
		Film film = getFilm(filmId);
		// удалить фильм из таблицы films_genres
		String sqlGenre = "DELETE FROM films_genres WHERE film_id=? ";
		jdbcTemplate.update(sqlGenre, filmId);
		// удалить фильм из таблицы films
		String sqlFilm = "DELETE FROM films WHERE film_id=? ";
		jdbcTemplate.update(sqlFilm, filmId);
		return film;
	}

	/*
	 * получить спискок фильмов по рейтингу
	 */
	@Override
	public List<Film> getRatedFilms(List<Long> ratedFilmsIdList) {
		List<Film> filmList = new ArrayList<>();
		for (int i = 0; i < ratedFilmsIdList.size(); i++) {
			Long filmId = ratedFilmsIdList.get(i);
			Film film = getFilm(filmId);
			filmList.add(film);
		}
		return filmList;
	}

	/*
	 * получить список всех фильмов
	 */
	@Override
	public List<Film> getAllFilms() {
		List<Film> filmList = new ArrayList<>();
		String sql = "SELECT id FROM films GROUP BY id";
		// получить id-список всех фильмов
		List<Long> filmIdList = jdbcTemplate.queryForList(sql, Long.class);
		// получение и добавление фильмов в список по id-списку
		for (int i = 0; i < filmIdList.size(); i++) {
			Long filmId = filmIdList.get(i);
			Film film = getFilm(filmId);
			filmList.add(film);
		}
		return filmList;
	}
}