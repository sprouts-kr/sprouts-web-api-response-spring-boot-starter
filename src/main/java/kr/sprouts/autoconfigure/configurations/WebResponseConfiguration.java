package kr.sprouts.autoconfigure.configurations;

import kr.sprouts.autoconfigure.properties.WebResponseProperty;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = { WebResponseProperty.class })
public class WebResponseConfiguration {

    private final WebResponseProperty webResponseProperty;

    public WebResponseConfiguration(WebResponseProperty webResponseProperty) {
        this.webResponseProperty = webResponseProperty;

        LoggerFactory.getLogger(WebResponseConfiguration.class)
                .info(String.format("Initialized %s", WebResponseConfiguration.class.getName()));
    }

    public String getId() {
        return this.webResponseProperty.getId();
    }
}
