package kr.sprouts.framework.autoconfigure.web.response.components.base;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class BaseException extends RuntimeException {
    private final String value;
    private final String reason;
    private final HttpStatus httpStatus;
}
