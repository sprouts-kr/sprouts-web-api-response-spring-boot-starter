package kr.sprouts.autoconfigure.components.header.sortable.dto;


import kr.sprouts.autoconfigure.components.header.sortable.exception.rollback.InvalidSortOptionException;

public enum SortDirection {
    ASC("ASC"),
    DESC("DESC");

    private final String value;

    SortDirection(String value) {
        this.value = value;
    }

    public static SortDirection fromValue(String value) {
        if (value.equalsIgnoreCase("ASC")) {
            return SortDirection.ASC;
        } else if (value.equalsIgnoreCase("DESC")) {
            return SortDirection.DESC;
        } else {
            throw InvalidSortOptionException.byDirection();
        }
    }
}
