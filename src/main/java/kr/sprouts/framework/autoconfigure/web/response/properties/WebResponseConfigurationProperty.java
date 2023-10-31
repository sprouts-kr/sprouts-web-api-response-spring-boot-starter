package kr.sprouts.framework.autoconfigure.web.response.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "sprouts.web.response")
@Getter @Setter
public class WebResponseConfigurationProperty {
    private String defaultHost;
    private List<Host> hosts;

    @Getter @Setter
    public static class Host {
        private String name;
        private String url;
    }
}
