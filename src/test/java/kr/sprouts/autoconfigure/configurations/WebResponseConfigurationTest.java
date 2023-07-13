package kr.sprouts.autoconfigure.configurations;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WebResponseConfigurationTest {
    private final ApplicationContextRunner applicationContextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(WebResponseConfiguration.class));

    @Test
    void configuration() {
        String[] properties = {
                "sprouts.web.response.default-host=sprouts-gateway",
                "sprouts.web.response.hosts[0].name=sprouts-gateway",
                "sprouts.web.response.hosts[0].url=http://gateway.framework.sprouts.kr"
        };

        this.applicationContextRunner.withPropertyValues(properties)
                .run(context-> assertThat(context).hasSingleBean(WebResponseConfiguration.class));
    }

    @Test
    void property() {
        String[] properties = {
                "sprouts.web.response.default-host=sprouts-gateway",
                "sprouts.web.response.hosts[0].name=sprouts-gateway",
                "sprouts.web.response.hosts[0].url=http://gateway.framework.sprouts.kr"
        };

        this.applicationContextRunner.withPropertyValues(properties)
                .run(context-> assertThat("sprouts-gateway".equals(context.getBean(WebResponseConfiguration.class).getDefaultHost())).isTrue());
    }

    @Test
    void exception() {
        String[] nonDefaultHostProperties = {
                "sprouts.web.response.hosts[0].name=sprouts-gateway",
                "sprouts.web.response.hosts[0].url=http://gateway.framework.sprouts.kr"
        };

        assertThatThrownBy(() -> {
            this.applicationContextRunner.withPropertyValues(nonDefaultHostProperties)
                    .run(context-> context.getBean(WebResponseConfiguration.class).getDefaultHost());
        }).isInstanceOf(IllegalStateException.class);


        String[] nonHostsProperties = {
                "sprouts.web.response.default-host=sprouts-gateway",
        };

        assertThatThrownBy(() -> {
            this.applicationContextRunner.withPropertyValues(nonHostsProperties)
                    .run(context-> context.getBean(WebResponseConfiguration.class).getDefaultHost());
        }).isInstanceOf(IllegalStateException.class);

        String[] invalidProperties = {
                "sprouts.web.response.default-host=sprouts-gateway",
                "sprouts.web.response.hosts[0].name=sprouts-gateway",
                "sprouts.web.response.hosts[0].url=gateway.framework.sprouts.kr"
        };

        assertThatThrownBy(() -> {
            this.applicationContextRunner.withPropertyValues(invalidProperties)
                    .run(context-> context.getBean(WebResponseConfiguration.class).getDefaultHost());
        }).isInstanceOf(IllegalStateException.class);

        String[] noMatchDefaultProperties = {
                "sprouts.web.response.default-host=sprouts",
                "sprouts.web.response.hosts[0].name=sprouts-gateway",
                "sprouts.web.response.hosts[0].url=http://gateway.framework.sprouts.kr",
                "sprouts.web.response.hosts[0].name=sprouts-gateway2",
                "sprouts.web.response.hosts[0].url=http://gateway.framework.sprouts.kr"
        };

        assertThatThrownBy(() -> {
            this.applicationContextRunner.withPropertyValues(noMatchDefaultProperties)
                    .run(context-> context.getBean(WebResponseConfiguration.class).getDefaultHost());
        }).isInstanceOf(IllegalStateException.class);
    }
}
