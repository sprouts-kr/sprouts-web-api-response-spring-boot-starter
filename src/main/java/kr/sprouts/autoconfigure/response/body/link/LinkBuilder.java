package kr.sprouts.autoconfigure.response.body.link;

import kr.sprouts.autoconfigure.properties.WebApiResponseProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class LinkBuilder {
    static String defaultHost = null;
    static Map<String, String> hosts = new HashMap<>();
    static private final Logger logger = LoggerFactory.getLogger(LinkBuilder.class);

    public LinkBuilder(WebApiResponseProperty WebApiResponseProperty) {
        LinkBuilder.defaultHost = WebApiResponseProperty.getDefaultHost();

        if (WebApiResponseProperty.getHosts() != null) {
            LinkBuilder.hosts = WebApiResponseProperty.getHosts().stream()
                    .filter(hostProperties -> hostProperties.getName() != null && hostProperties.getUrl() != null)
                    .filter(hostProperties -> this.isValidUrl(hostProperties.getUrl()))
                    .collect(Collectors.toMap(
                            kr.sprouts.autoconfigure.properties.WebApiResponseProperty.Host::getName,
                            kr.sprouts.autoconfigure.properties.WebApiResponseProperty.Host::getUrl,
                            (prev, current) -> prev
                    ));
        }
    }

    private boolean isValidUrl(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    public static ReferenceLink fromDefaultHost(String description, String path, HttpMethod method) {
        String host = LinkBuilder.getDefaultHost();

        if (host == null) {
            return ReferenceLink.getEmptyInstance();
        }

        else {
            URI uri = URI.create(host + path);
            return ReferenceLink.of(description, uri, method);
        }
    }

    public static ReferenceLink fromHost(String hostName, String description, String path, HttpMethod method) {
        String host = LinkBuilder.getHost(hostName);

        if (host == null) {
            logger.error(String.format( "Cannot create link. Host named \"%s\" not matched in host", hostName));
            return ReferenceLink.getEmptyInstance();
        }

        URI uri = URI.create(host + path);
        return ReferenceLink.of(description, uri, method);
    }

    public static String getHost(String hostName) {
        String host = LinkBuilder.hosts.get(hostName);

        if (host == null) {
            logger.warn(String.format("Cannot find host named \"%s\"", hostName));
        }

        return host;
    }

    public static String getDefaultHost() {
        if (LinkBuilder.defaultHost == null) {
            logger.error("Cannot create link from default host. Default host not registered.");
            return null;
        }

        if (LinkBuilder.hosts.get(LinkBuilder.defaultHost) == null) {
            logger.error(String.format("Cannot create link from default host. Default host \"%s\" not matched in host", LinkBuilder.defaultHost));
            return null;
        }

        return LinkBuilder.hosts.get(LinkBuilder.defaultHost);
    }
}
