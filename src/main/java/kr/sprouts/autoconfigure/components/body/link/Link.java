package kr.sprouts.autoconfigure.components.body.link;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.io.Serializable;
import java.net.URI;

@AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
public class Link implements Serializable {

    @Getter
    private URI href;

    @Getter
    private HttpMethod method;

    private MediaType mediaType;

    public String getMediaType() {
        return String.format("%s/%s",
                this.mediaType.getType(),
                this.mediaType.getSubtype()
        );
    }

    public static Link fromReferenceLink(ReferenceLink referenceLink) {
        return Link.of(referenceLink.getPath(), referenceLink.getMethod(), referenceLink.getMediaType());
    }
}
