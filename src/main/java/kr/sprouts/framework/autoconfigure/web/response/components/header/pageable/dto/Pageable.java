package kr.sprouts.framework.autoconfigure.web.response.components.header.pageable.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Pageable {
    private Integer offset;
    private Integer limit;

    public static Pageable fromProxy(PageableProxy proxy) {
        if (proxy == null) {
            return new Pageable(null, null);
        }

        return new Pageable(
                proxy.getOffset(),
                proxy.getLimit()
        );
    }
}
