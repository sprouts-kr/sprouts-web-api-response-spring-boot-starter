package kr.sprouts.framework.autoconfigure.web.response.components.header.pageable.dto;

import kr.sprouts.framework.autoconfigure.web.response.components.header.pageable.exception.rollback.InvalidPageOptionException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageableProxy {
    @Getter(AccessLevel.PACKAGE)
    private Integer offset;
    @Getter(AccessLevel.PACKAGE)
    private Integer limit;

    public static PageableProxy fromRequest(PageableRequest request) {
        if (request == null) {
            return new PageableProxy(null, null);
        }

        return new PageableProxy(
                PageableProxy.converseValue(request.getOffset()),
                PageableProxy.converseValue(request.getLimit())
        );
    }

    private static Integer converseValue(String string) {
        try {
            if (string == null) {
                return null;
            }
            return Integer.parseInt(string);
        } catch (Exception e) {
            throw InvalidPageOptionException.byValue();
        }
    }
}
