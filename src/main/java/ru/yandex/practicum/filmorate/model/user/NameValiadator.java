package ru.yandex.practicum.filmorate.model.user;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class NameValidator implements ConstraintValidator<Name, User> {

	@Override
	public boolean isValid(User value, ConstraintValidatorContext context) {

		context.disableDefaultConstraintViolation();

		if (value.getName() == null) {
			value.setName(value.getLogin());
			logInfo(value.getName());
			return true;
		} else if (value.getName().isBlank() || value.getName().isEmpty()) {
			value.setName(value.getLogin());
			logInfo(value.getName());
			return true;
		} else {
			return true;
		}
	}

	private void logInfo(String name) {
		log.warn("Имя не было задано. В качестве имени используется логин " + name);
	}
}