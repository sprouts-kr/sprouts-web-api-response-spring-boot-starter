package kr.sprouts.autoconfigure.configurations;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WebApiResponseConfigurationTest {
    private final ApplicationContextRunner applicationContextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(WebApiResponseConfiguration.class));

    @Test
    void configuration() {
        String[] properties = {
                "sprouts.web.response.default-host=sprouts-gateway",
                "sprouts.web.response.hosts[0].name=sprouts-gateway",
                "sprouts.web.response.hosts[0].url=http://gateway.framework.sprouts.kr"
        };

        this.applicationContextRunner.withPropertyValues(properties)
                .run(context-> assertThat(context).hasSingleBean(WebApiResponseConfiguration.class));
    }
}
