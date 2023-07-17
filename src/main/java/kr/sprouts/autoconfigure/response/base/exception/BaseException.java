package kr.sprouts.autoconfigure.response.base.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class BaseException extends RuntimeException {

    @Getter
    private final String value;
    @Getter
    private final String reason;
    @Getter
    private final HttpStatus httpStatus;
}
