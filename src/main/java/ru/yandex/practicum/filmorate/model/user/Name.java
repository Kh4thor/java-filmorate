package ru.yandex.practicum.filmorate.model.user;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import lombok.extern.slf4j.Slf4j;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidator.class)
@interface Name {

	Class<?>[] groups() default {};

	String message()

	default "{NameValidation.message}";

	Class<? extends Payload>[] payload() default {};
}

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