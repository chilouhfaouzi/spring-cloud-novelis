package io.novelis.smartroby.sample.service.validation.exception.common;

public class DataNotFoundException extends BusinessException {

    public DataNotFoundException(String message, Object... args) {
        super(404, "data.not.found", message, args);
    }

    public DataNotFoundException() {
        this("data.not.found");
    }
}
