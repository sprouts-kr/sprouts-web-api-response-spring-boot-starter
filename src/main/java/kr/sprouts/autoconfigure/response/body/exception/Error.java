package kr.sprouts.autoconfigure.response.body.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Error {

    @Getter
    private final String value;
    @Getter
    private final String reason;
}
