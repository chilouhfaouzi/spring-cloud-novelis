package io.novelis.smartroby.sample.service.validation.exception.common;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    protected final int code;
    protected final String title;
    protected final transient Object[] args;

    public BusinessException(int code, String title, String message, Object... args) {
        super(message);
        this.code = code;
        this.title = title;
        this.args = args;
    }

    public Object[] getArgs() {
        if (args != null) {
            return args.clone();
        }
        return new Object[0];
    }
}
