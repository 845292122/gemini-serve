package net.gemini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 * @author edison
 */
@SpringBootApplication
@EnableTransactionManagement
public class GeminiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeminiApplication.class, args);
    }
}