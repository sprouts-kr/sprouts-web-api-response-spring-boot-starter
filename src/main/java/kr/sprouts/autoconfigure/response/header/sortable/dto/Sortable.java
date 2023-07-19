package kr.sprouts.autoconfigure.response.header.sortable.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Sortable {

    @Getter
    private List<SortOption> sortBy;

    public static Sortable fromProxy(SortableProxy proxy) {
        if (proxy == null || proxy.getSortBy() == null) {
            return new Sortable(Collections.emptyList());
        }

        return new Sortable(
                proxy.getSortBy()
        );
    }

    public SortDirection getDirection(String name) {
        if (this.sortBy == null) {
            return null;
        }

        Optional<SortOption> result = this.sortBy.stream()
                .filter(option -> option.getName().equals(name))
                .findFirst();

        return result.map(SortOption::getDirection).orElse(null);
    }
}
