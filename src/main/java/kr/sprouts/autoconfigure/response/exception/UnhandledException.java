package kr.sprouts.autoconfigure.response.exception;

import kr.sprouts.autoconfigure.response.base.BaseRollbackException;
import org.springframework.http.HttpStatus;

public class UnhandledException extends BaseRollbackException {
    private UnhandledException(Throwable t) {
        super("-1", String.format("%s: %s", t.getClass().toString(), t.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private static UnhandledException of(Throwable t) {
        return new UnhandledException(t);
    }
}
