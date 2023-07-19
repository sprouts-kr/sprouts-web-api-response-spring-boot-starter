package kr.sprouts.autoconfigure.response.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.sprouts.autoconfigure.response.base.exception.BaseException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionStructuredResponseBody extends StructuredResponseBody {

    @Getter
    private Error error;

    private ExceptionStructuredResponseBody(BaseException exception) {
        super(false);
        this.error = new Error(exception.getValue(), exception.getReason());
    }

    public static ExceptionStructuredResponseBody of(BaseException exception) {
        return new ExceptionStructuredResponseBody(exception);
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class Error {
        @Getter
        private final String value;
        @Getter
        private final String reason;
    }
}
