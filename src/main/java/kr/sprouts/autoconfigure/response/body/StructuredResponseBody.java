package kr.sprouts.autoconfigure.response.body;

import kr.sprouts.autoconfigure.response.body.link.Link;
import kr.sprouts.autoconfigure.response.body.link.ReferenceLink;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StructuredResponseBody {

    @Getter
    private boolean succeeded;

    @Getter
    private HashMap<String, Link> links;

    protected StructuredResponseBody(boolean succeeded) {
        this.succeeded = succeeded;
        this.links = null;
    }

    private void initInnerLink() {
        if (this.links == null) {
            this.links = new HashMap<>();
        }
    }

    private void addInnerLink(ReferenceLink referenceLink) {
        this.links.put(
                referenceLink.getDescription(),
                Link.fromReferenceLink(referenceLink)
        );
    }

    public StructuredResponseBody addLink(ReferenceLink referenceLink) {
        this.initInnerLink();
        this.addInnerLink(referenceLink);
        return this;
    }

    public StructuredResponseBody addLinks(ReferenceLink... referenceLinks) {
        this.initInnerLink();

        for (ReferenceLink referenceLink: referenceLinks) {
            this.addInnerLink(referenceLink);
        }

        return this;
    }
}
