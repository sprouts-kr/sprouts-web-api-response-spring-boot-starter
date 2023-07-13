package kr.sprouts.autoconfigure.configurations;

import kr.sprouts.autoconfigure.properties.WebResponseProperty;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Configuration
@EnableConfigurationProperties(value = { WebResponseProperty.class })
public class WebResponseConfiguration {

    private final WebResponseProperty webResponseProperty;

    public WebResponseConfiguration(WebResponseProperty webResponseProperty) {
        this.webResponseProperty = webResponseProperty;

        if (StringUtils.isBlank(this.webResponseProperty.getDefaultHost())) {
            throw new IllegalArgumentException("Property sprouts.web.response.default-host is required.");
        }

        if (this.webResponseProperty.getHosts() == null || webResponseProperty.getHosts().size() == 0) {
            throw new IllegalArgumentException("Property sprouts.web.response.hosts is required.");
        }

        if (this.webResponseProperty.getHosts().stream().filter(host -> StringUtils.isNotBlank(host.getName()) && StringUtils.isNotBlank(host.getUrl()) && this.isValidUrl(host.getUrl())).count() != webResponseProperty.getHosts().size()) {
            throw new IllegalArgumentException("Property sprouts.web.response.hosts has an invalid host defined.");
        }

        if (this.webResponseProperty.getHosts().stream().noneMatch(host -> this.webResponseProperty.getDefaultHost().equals(host.getName()))) {
            throw new IllegalArgumentException("No match for sprouts.web.response.default-host in sprouts.web.response.hosts");
        }

        LoggerFactory.getLogger(WebResponseConfiguration.class)
                .info(String.format("Initialized %s", WebResponseConfiguration.class.getName()));
    }

    private boolean isValidUrl(String url) {

        try {
            new URL(url);
            return true;
        }
        catch (MalformedURLException e) {
            return false;
        }
    }

    public String getDefaultHost() {
        return this.webResponseProperty.getDefaultHost();
    }

    public List<WebResponseProperty.Host> getHosts() {
        return this.webResponseProperty.getHosts();
    }
}
