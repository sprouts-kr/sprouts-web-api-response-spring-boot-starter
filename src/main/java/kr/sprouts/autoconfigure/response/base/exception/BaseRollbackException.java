package kr.sprouts.autoconfigure.response.base.exception;

import org.springframework.http.HttpStatus;

public class BaseRollbackException extends BaseException {

    public BaseRollbackException(String value, String reason, HttpStatus httpStatus) {
        super(value, reason, httpStatus);
    }
}
