package net.gemini.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 过滤器配置
 * @author edison
 */
@Configuration
public class FilterConfig {

    @Value("${gemini.request-id-key}")
    private String requestIdKey;
}
