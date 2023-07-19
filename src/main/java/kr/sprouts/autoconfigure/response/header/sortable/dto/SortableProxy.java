package kr.sprouts.autoconfigure.response.header.sortable.dto;

import kr.sprouts.autoconfigure.response.header.sortable.exception.rollback.InvalidSortOptionException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SortableProxy {

    @Getter(AccessLevel.PACKAGE)
    private List<SortOption> sortBy;

    public static SortableProxy fromRequest(SortableRequest request) {
        if (request == null || request.getSortBy() == null) {
            return new SortableProxy(Collections.emptyList());
        }

        return new SortableProxy(
                request.getSortBy().stream()
                        .map(optionString -> {
                            String[] options = optionString.split("\\.");

                            if (options.length != 2) {
                                throw InvalidSortOptionException.byLength();
                            }

                            return SortableProxy.converseValue(options[0], options[1]);
                        })
                        .collect(Collectors.toList())
        );
    }

    private static SortOption converseValue(String name, String direction) {
        return SortOption.of(
                name,
                SortDirection.fromValue(direction)
        );
    }
}
