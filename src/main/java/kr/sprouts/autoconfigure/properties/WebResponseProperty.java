package kr.sprouts.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "sprouts.web.response")
public class WebResponseProperty {

    @Getter @Setter
    private String defaultHost;
    @Getter @Setter
    private List<Host> hosts;

    public static class Host {
        @Getter @Setter
        private String name;
        @Getter @Setter
        private String url;
    }
}
