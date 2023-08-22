package kr.sprouts.autoconfigure.components.header.pageable.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Pageable {

    @Getter
    private Integer offset;
    @Getter
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
