package kr.sprouts.framework.autoconfigure.web.response.components.header.sortable.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SortOption {
    private String name;
    private SortDirection direction;

    public static SortOption of(String name, SortDirection direction) {
        return new SortOption(name, direction);
    }
}
