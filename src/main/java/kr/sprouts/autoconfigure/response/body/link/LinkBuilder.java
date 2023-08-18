package kr.sprouts.autoconfigure.response.body.link;

import kr.sprouts.autoconfigure.properties.WebApiResponseProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class LinkBuilder {
    private final WebApiResponseProperty webApiResponseProperty;
    static String defaultHost = null;
    static Map<String, String> hosts = new HashMap<>();

    public LinkBuilder(WebApiResponseProperty webApiResponseProperty) {
        this.webApiResponseProperty = webApiResponseProperty;

        LinkBuilder.defaultHost = this.webApiResponseProperty.getDefaultHost();

        if (this.webApiResponseProperty.getHosts() != null) {
            LinkBuilder.hosts = this.webApiResponseProperty.getHosts().stream()
                    .filter(host -> host.getName() != null && host.getUrl() != null)
                    .filter(host -> this.isValidUrl(host.getUrl()))
                    .collect(Collectors.toMap(
                            WebApiResponseProperty.Host::getName,
                            WebApiResponseProperty.Host::getUrl,
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
            if (log.isErrorEnabled()) log.error("Cannot create link. Host named '{}' not matched in host", hostName);

            return ReferenceLink.getEmptyInstance();
        }

        URI uri = URI.create(host + path);
        return ReferenceLink.of(description, uri, method);
    }

    public static String getHost(String hostName) {
        String host = LinkBuilder.hosts.get(hostName);

        if (host == null) {
            if(log.isWarnEnabled()) log.warn("Cannot find host named '{}'", hostName);

            return null;
        }

        return host;
    }

    public static String getDefaultHost() {
        if (LinkBuilder.defaultHost == null) {
            if (log.isErrorEnabled()) log.error("Cannot create link from default host. Default host not registered.");

            return null;
        }

        if (LinkBuilder.hosts.get(LinkBuilder.defaultHost) == null) {
            if (log.isErrorEnabled()) log.error("Cannot create link from default host. Default host '{}' not matched in host", LinkBuilder.defaultHost);

            return null;
        }

        return LinkBuilder.hosts.get(LinkBuilder.defaultHost);
    }
}
