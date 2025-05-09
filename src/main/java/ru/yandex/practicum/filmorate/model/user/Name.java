package ru.yandex.practicum.filmorate.model.user;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidator.class)
public @interface Name {

	Class<?>[] groups() default {};

	String message()

	default "{NameValidation.message}";

	Class<? extends Payload>[] payload() default {};
}