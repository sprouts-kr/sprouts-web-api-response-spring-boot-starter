package kr.sprouts.autoconfigure.components.base;

import kr.sprouts.autoconfigure.components.body.link.Link;
import kr.sprouts.autoconfigure.components.body.link.ReferenceLink;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseResponse {

    @Getter
    private HashMap<String, Link> links;

    @Getter
    private UUID id;

    protected BaseResponse(UUID id) {
        this.id = id;
        this.links = null;
    }

    private void initInnerLinks() {
        if (this.links == null) {
            this.links = new HashMap<>();
        }
    }

    private void addInnerLink(ReferenceLink link) {
        this.links.put(
                link.getDescription(),
                Link.fromReferenceLink(link)
        );
    }

    public BaseResponse addLink(ReferenceLink link) {
        this.initInnerLinks();
        this.addInnerLink(link);

        return this;
    }

    public BaseResponse addLinks(ReferenceLink ...links) {
        this.initInnerLinks();

        for (ReferenceLink link: links) {
            this.addInnerLink(link);
        }

        return this;
    }
}
