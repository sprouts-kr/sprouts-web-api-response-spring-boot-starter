package kr.sprouts.autoconfigure.configurations;

import kr.sprouts.autoconfigure.properties.WebApiResponseProperty;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Configuration
@EnableConfigurationProperties(value = { WebApiResponseProperty.class })
public class WebApiResponseConfiguration {

    private final WebApiResponseProperty webApiResponseProperty;

    public WebApiResponseConfiguration(WebApiResponseProperty webApiResponseProperty) {
        this.webApiResponseProperty = webApiResponseProperty;

        if (StringUtils.isBlank(this.webApiResponseProperty.getDefaultHost())) {
            throw new IllegalArgumentException("Property sprouts.web.response.default-host is required.");
        }

        if (this.webApiResponseProperty.getHosts() == null || webApiResponseProperty.getHosts().size() == 0) {
            throw new IllegalArgumentException("Property sprouts.web.response.hosts is required.");
        }

        if (this.webApiResponseProperty.getHosts().stream().filter(host -> StringUtils.isNotBlank(host.getName()) && StringUtils.isNotBlank(host.getUrl()) && this.isValidUrl(host.getUrl())).count() != webApiResponseProperty.getHosts().size()) {
            throw new IllegalArgumentException("Property sprouts.web.response.hosts has an invalid host defined.");
        }

        if (this.webApiResponseProperty.getHosts().stream().noneMatch(host -> this.webApiResponseProperty.getDefaultHost().equals(host.getName()))) {
            throw new IllegalArgumentException("No match for sprouts.web.response.default-host in sprouts.web.response.hosts");
        }

        LoggerFactory.getLogger(WebApiResponseConfiguration.class)
                .info(String.format("Initialized %s", WebApiResponseConfiguration.class.getName()));
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
        return this.webApiResponseProperty.getDefaultHost();
    }

    public List<WebApiResponseProperty.Host> getHosts() {
        return this.webApiResponseProperty.getHosts();
    }
}
