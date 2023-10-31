package kr.sprouts.framework.autoconfigure.web.response.components.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.sprouts.framework.autoconfigure.web.response.components.base.BaseException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ExceptionStructuredResponseBody extends StructuredResponseBody {
    private Error error;

    private ExceptionStructuredResponseBody(BaseException exception) {
        super(false);
        this.error = new Error(exception.getValue(), exception.getReason());
    }

    public static ExceptionStructuredResponseBody of(BaseException exception) {
        return new ExceptionStructuredResponseBody(exception);
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    private static class Error {
        private final String value;
        private final String reason;
    }
}
