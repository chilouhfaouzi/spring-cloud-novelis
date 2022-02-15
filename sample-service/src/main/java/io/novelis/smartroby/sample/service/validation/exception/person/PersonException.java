package io.novelis.smartroby.sample.service.validation.exception.person;

import io.novelis.smartroby.sample.service.validation.exception.common.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class PersonException extends BusinessException {

    public PersonException(PersonErrorsEnum error, Object... args) {
        super(error.getCode(), error.getTitle(), error.getMessage(), args);
    }

    @Getter
    @AllArgsConstructor
    public enum PersonErrorsEnum {
        PERSON_FIRSTNAME_LENGTH_INVALID(100, "person.validation.error", "person.firstname.length.invalid");

        private final int code;
        private final String title;
        private final String message;
    }
}
