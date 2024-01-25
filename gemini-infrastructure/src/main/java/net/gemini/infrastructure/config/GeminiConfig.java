package net.gemini.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author edison
 */
@Data
@Component
@ConfigurationProperties(prefix = "gemini")
public class GeminiConfig {

    private String name;
    private String version;
    private String uploadDir;
}
