package kr.sprouts.autoconfigure.response.header.sortable.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SortOption {

    @Getter
    private String name;

    @Getter
    private SortDirection direction;

    public static SortOption of(String name, SortDirection direction) {
        return new SortOption(name, direction);
    }
}
