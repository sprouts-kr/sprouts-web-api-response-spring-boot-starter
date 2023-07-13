package kr.sprouts.autoconfigure.response.header.pageable.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageableRequest {

    @Getter(AccessLevel.PACKAGE)
    private String offset;

    @Getter(AccessLevel.PACKAGE)
    private String limit;

    public static PageableRequest fromString(String string) {
        if (string == null) {
            return new PageableRequest(null, null);
        }

        String offset = null;
        String limit = null;

        String[] params = string.split(";");

        for (int i = 0; i < params.length; i++) {
            String[] param = params[i].split("=");

            if (param.length == 2) {
                String name = param[0].trim();
                String value = param[1].trim();

                if (name.equalsIgnoreCase("offset")) {
                    offset = value;
                }

                else if (name.equalsIgnoreCase("limit")) {
                    limit = value;
                }
            }
        }

        return new PageableRequest(offset, limit);
    }
}
