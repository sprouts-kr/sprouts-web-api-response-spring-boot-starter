package kr.sprouts.framework.autoconfigure.web.response.components.body.link;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.net.URI;

@AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
@Getter
public class ReferenceLink {
    private String description;
    private URI path;
    private HttpMethod method;
    private MediaType mediaType;

    // singleton instance
    private static ReferenceLink empty = null;

    protected static ReferenceLink of(String description, URI path, HttpMethod method) {
        return ReferenceLink.of(description, path, method, MediaType.APPLICATION_JSON);
    }

    public static ReferenceLink getEmptyInstance() {
        if (ReferenceLink.empty == null) {
            ReferenceLink.empty = ReferenceLink.of(null, null, null, null);
        }

        return ReferenceLink.empty;
    }

    public ReferenceLink withMediaType(MediaType mediaType) {
        if (!this.equals(ReferenceLink.empty)) {
            this.mediaType = mediaType;
        }

        return this;
    }
}
