package kr.sprouts.autoconfigure.response.body.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.sprouts.autoconfigure.response.base.exception.BaseException;
import kr.sprouts.autoconfigure.response.body.StructuredResponseBody;
import lombok.AccessLevel;
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
}
