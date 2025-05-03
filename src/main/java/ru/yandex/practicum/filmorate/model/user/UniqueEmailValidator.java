package ru.yandex.practicum.filmorate.model.user;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.storage.impl.UsersStorageService;

/*
 * Аннотация @UniqueEmail связана с email-списком класса UserStorageService
 */
@Slf4j
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, User> {

	@Autowired
	UsersStorageService userStorageService;

	@Override
	public boolean isValid(User value, ConstraintValidatorContext context) {

		boolean isEmailListContainsSuchEmail = userStorageService.getEmailList().contains(value.getEmail());
		boolean isUserStorageContainsUserWithSuchId = userStorageService.getRepository().containsKey(value.getId());

		if (isEmailListContainsSuchEmail && !isUserStorageContainsUserWithSuchId) {
			log.warn("Пользователь с таким email уже зарегестрирован");
			return false;
		} else {
			return true;
		}
	}

//		if (isEmailListContainsSuchEmail && !isUserStorageContainsUserWithSuchId) {
//			log.warn("Пользователь с таким email уже зарегестрирован");
//			return false;
//		} else if (isEmailListContainsSuchEmail && isUserStorageContainsUserWithSuchId) {
//			return true;
//		}
//		return false;
//	}
}