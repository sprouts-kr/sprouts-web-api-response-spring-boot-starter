package kr.sprouts.framework.autoconfigure.web.response.configurations;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class WebResponseConfigurationTest {
    private final ApplicationContextRunner applicationContextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(WebResponseConfiguration.class));

    @Test
    void configuration() {
        String[] properties = {
                "sprouts.web.response.default-host=sprouts-gateway",
                "sprouts.web.response.hosts[0].name=sprouts-gateway",
                "sprouts.web.response.hosts[0].url=http://gateway.framework.local"
        };

        this.applicationContextRunner.withPropertyValues(properties)
                .run(context-> assertThat(context).hasSingleBean(WebResponseConfiguration.class));
    }
}
