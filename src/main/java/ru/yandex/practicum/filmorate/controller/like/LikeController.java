package ru.yandex.practicum.filmorate.controller.like;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/films")
public class LikeController {

	@PutMapping("/{id}/like/{userId}")
	public void setLike() {

	}
}
