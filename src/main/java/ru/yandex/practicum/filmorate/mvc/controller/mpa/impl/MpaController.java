package ru.yandex.practicum.filmorate.mvc.controller.mpa.impl;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.mpa.Mpa;
import ru.yandex.practicum.filmorate.mvc.controller.mpa.MpaAppController;
import ru.yandex.practicum.filmorate.mvc.service.mpa.MpaAppService;

@Slf4j
@RestController
@RequestMapping("/mpa")
public class MpaController implements MpaAppController {

	private final MpaAppService mpaAppService;

	public MpaController(MpaAppService mpaAppService) {
		this.mpaAppService = mpaAppService;
	}

	@Override
	@GetMapping("/{mpaId}")
	public Mpa getMpa(int mpaId) {
		log.info("Начат процесс вызова рейтинга фильма id=" + mpaId);
		return mpaAppService.getMpa(mpaId);
	}

	@Override
	@GetMapping
	public List<Mpa> getAllMpa() {
		log.info("Начат процесс вызова списка всех рейтингов фильма");
		return mpaAppService.getAllMpa();
	}
}
