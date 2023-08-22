package kr.sprouts.framework.autoconfigure.web.response.components.header.sortable.exception.rollback;

import kr.sprouts.framework.autoconfigure.web.response.components.base.BaseRollbackException;
import org.springframework.http.HttpStatus;

public class InvalidSortOptionException extends BaseRollbackException {
    private InvalidSortOptionException(String reason) {
        super("invalid_sort_option", reason, HttpStatus.BAD_REQUEST);
    }

    public static InvalidSortOptionException byLength() {
        return new InvalidSortOptionException("Sort option should be consisted of {name}.{direction}");
    }

    public static InvalidSortOptionException byDirection() {
        return new InvalidSortOptionException("Direction should be asc or desc.");
    }
}
