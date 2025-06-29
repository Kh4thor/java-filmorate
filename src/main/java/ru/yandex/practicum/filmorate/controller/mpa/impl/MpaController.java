package ru.yandex.practicum.filmorate.controller.mpa.impl;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.controller.mpa.MpaAppController;
import ru.yandex.practicum.filmorate.model.film.Mpa;
import ru.yandex.practicum.filmorate.service.mpa.MpaAppService;

@Slf4j
@RestController
@RequestMapping("/mpa")
public class MpaController implements MpaAppController<Mpa> {

	private final MpaAppService<Mpa> mpaAppService;

	public MpaController(MpaAppService<Mpa> mpaAppService) {
		this.mpaAppService = mpaAppService;
	}

	@Override
	@GetMapping("/{mpaId}")
	public Mpa getMpa(int mpaId) {
		return mpaAppService.getMpa(mpaId);
	}

	@Override
	@GetMapping
	public List<Mpa> getAllMpa() {
		return mpaAppService.getAllMpa();
	}
}
