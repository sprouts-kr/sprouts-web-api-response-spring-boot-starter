package kr.sprouts.autoconfigure.response.entity;

import kr.sprouts.autoconfigure.response.base.BaseResponse;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Collection;

public class StructuredPagedResponseEntity<C extends Collection<? extends BaseResponse>> {

    @Getter
    private final static HttpStatus httpStatus = HttpStatus.OK;
    @Getter(AccessLevel.PACKAGE)
    private final C baseResponseBody;

    private StructuredPagedResponseEntity(C baseResponses) {
        this.baseResponseBody = baseResponses;
    }

    static <C extends Collection<? extends BaseResponse>> StructuredPagedResponseEntity createFromMultiResponse(C responses) {
        return new StructuredPagedResponseEntity(responses);
    }

    public StructuredResponseEntity withTotalSize(Long totalSize) {
        return StructuredResponseEntity.createFromStructuredPagedResponseEntity(
                this,
                totalSize
        );
    }
}
