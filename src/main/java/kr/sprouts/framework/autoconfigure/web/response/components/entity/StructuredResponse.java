package kr.sprouts.framework.autoconfigure.web.response.components.entity;

import kr.sprouts.framework.autoconfigure.web.response.components.base.BaseException;
import kr.sprouts.framework.autoconfigure.web.response.components.base.BaseResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StructuredResponse {

    public static <C extends Collection<? extends BaseResponse>> StructuredResponseEntity succeeded(C responses) {
        return StructuredResponseEntity.createFromMultiResponse(responses, HttpStatus.OK);
    }

    public static <C extends Collection<? extends BaseResponse>> StructuredPagedResponseEntity<C> paged(C responses) {
        return StructuredPagedResponseEntity.createFromMultiResponse(responses);
    }

    public static <T extends BaseResponse> StructuredResponseEntity succeeded(T response) {
        return StructuredResponseEntity.createFromSingleResponse(response, HttpStatus.OK);
    }

    public static StructuredResponseEntity deleted() {
        return StructuredResponseEntity.createFromHttpStatus(HttpStatus.NO_CONTENT);
    }

    public static <E extends BaseException> StructuredResponseEntity error(E exception) {
        return StructuredResponseEntity.createFromExceptionResponse(exception);
    }

    public static <T extends BaseResponse> StructuredCreateResponseEntity created(T response) {
        return StructuredCreateResponseEntity.createFromSingleResponse(response);
    }
}
