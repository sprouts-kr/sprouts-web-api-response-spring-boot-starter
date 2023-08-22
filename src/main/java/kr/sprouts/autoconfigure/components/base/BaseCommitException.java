package kr.sprouts.autoconfigure.components.base;

import org.springframework.http.HttpStatus;

public class BaseCommitException extends BaseException {

    public BaseCommitException(String value, String reason, HttpStatus httpStatus) {
        super(value, reason, httpStatus);
    }
}
