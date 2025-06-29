package ru.yandex.practicum.filmorate.storage.film.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.film.Genre;
import ru.yandex.practicum.filmorate.model.film.GenreConverter;
import ru.yandex.practicum.filmorate.model.film.Mpa;
import ru.yandex.practicum.filmorate.model.film.MpaConverter;
import ru.yandex.practicum.filmorate.storage.film.FilmAppStorage;

@Repository
@Component
public class DbFilmStorage implements FilmAppStorage<Film> {

	private final JdbcTemplate jdbcTemplate;
	private final MpaConverter mpaConverter;
	private final GenreConverter genreConverter;

	public DbFilmStorage(JdbcTemplate jdbcTemplate, MpaConverter mpaConverter, GenreConverter genreConverter) {
		this.jdbcTemplate = jdbcTemplate;
		this.mpaConverter = mpaConverter;
		this.genreConverter = genreConverter;
	}

	/*
	 * добавть фильм
	 */
	@Override
	public Film addFilm(Film film) {
		// добавление фильма в таблицу films
		String sqlFilm = "INSERT INTO films (id, name, description, release, duration, mpa) VALUES (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlFilm, film.getId(), film.getName(), film.getDescription(), film.getReleaseDate(),
				film.getDuration(), film.getMpa().getId());
		addGenres(film);
		return getFilm(film.getId());
	}

	/*
	 * добавление жанров фильма в промежуточную таблицу films_genres
	 */
	private void addGenres(Film film) {
		String sqlGenre = "INSERT INTO films_genres (film_id, genre_id) VALUES (?,?)";
		List<Genre> genreIdList = film.getGenres();
		// заполнение таблицы films_genres для каждого значения id-жанра
		for (int i = 0; i < genreIdList.size(); i++) {
			int genreId = genreIdList.get(i).getId();
			jdbcTemplate.update(sqlGenre, film.getId(), genreId);
		}
	}

	/*
	 * обновить фильм
	 */
	@Override
	public Film updateFilm(Film film) {
		removeFilm(film.getId());
		return addFilm(film);
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
		Film film = jdbcTemplate.queryForObject(sqlFilm, new FilmMapper(mpaConverter, genreConverter), filmId);
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

		int mpaId = film.getMpa().getId();
		String sqlNameMpa = "SELECT name FROM mpa WHERE id=?";
		// присвоить имя для mpa по id-mpa
		String nameMpa = jdbcTemplate.queryForObject(sqlNameMpa, String.class, mpaId);
		Mpa mpa = film.getMpa();
		mpa.setName(nameMpa);
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
		String sqlFilm = "DELETE FROM films WHERE id=? ";
		jdbcTemplate.update(sqlFilm, filmId);
		return film;
	}

	/*
	 * получить спискок фильмов по рейтингу
	 */
	@Override
	public List<Film> getRatedFilms(List<Long> ratedFilmsIdList) {
		String sql = "SELECT * FROM films WHERE id IN ("
				+ ratedFilmsIdList.stream().map(id -> "?").collect(Collectors.joining(",")) + ")";
		return jdbcTemplate.query(sql, new FilmMapper(mpaConverter, genreConverter), ratedFilmsIdList.toArray());
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