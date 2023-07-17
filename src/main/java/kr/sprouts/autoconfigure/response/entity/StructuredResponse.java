package kr.sprouts.autoconfigure.response.entity;

import kr.sprouts.autoconfigure.response.base.BaseResponse;
import kr.sprouts.autoconfigure.response.base.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
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

    public static <T extends BaseResponse> StructuredResponseEntity deleted() {
        return StructuredResponseEntity.createFromHttpStatus(HttpStatus.NO_CONTENT);
    }

    public static <E extends BaseException> StructuredResponseEntity error(E exception) {
        return StructuredResponseEntity.createFromExceptionResponse(exception);
    }

    public static <T extends BaseResponse> StructuredCreateResponseEntity created(T response) {
        return StructuredCreateResponseEntity.createFromSingleResponse(response);
    }
}
