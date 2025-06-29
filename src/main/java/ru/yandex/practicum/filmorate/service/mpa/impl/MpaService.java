package ru.yandex.practicum.filmorate.service.mpa.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.ExceptionAppChecker;
import ru.yandex.practicum.filmorate.model.film.Mpa;
import ru.yandex.practicum.filmorate.service.mpa.MpaAppService;
import ru.yandex.practicum.filmorate.storage.mpa.MpaAppStorage;

@Service
public class MpaService implements MpaAppService<Mpa> {

	private final MpaAppStorage mpaAppStorage;
	private final ExceptionAppChecker exceptionsAppChecker;

	public MpaService(ExceptionAppChecker exceptionsAppChecker, MpaAppStorage mpaAppStorage) {
		this.mpaAppStorage = mpaAppStorage;
		this.exceptionsAppChecker = exceptionsAppChecker;
	}

	@Override
	public Mpa getMpa(int mpaId) {
		String errorMessage = "Невозможно получить рейтинг фильма";
		exceptionsAppChecker.checkMpaValueIsOutOfRangeException(mpaId, errorMessage);
		return mpaAppStorage.getMpa(mpaId);
	}

	@Override
	public List<Mpa> getAllMpa() {
		return mpaAppStorage.getAllMpa();
	}
}
