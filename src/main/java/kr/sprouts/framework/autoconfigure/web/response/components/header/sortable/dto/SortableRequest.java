package kr.sprouts.framework.autoconfigure.web.response.components.header.sortable.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SortableRequest {

    @Getter(AccessLevel.PACKAGE)
    List<String> sortBy;

    public static SortableRequest fromString(String string) {
        if (string == null) {
            return new SortableRequest(Collections.emptyList());
        }

        String[] params = string.split(",");

        return new SortableRequest(
                Arrays.stream(params)
                        .map(String::trim)
                        .filter(param -> param.length() != 0)
                        .collect(Collectors.toList())
        );
    }
}
