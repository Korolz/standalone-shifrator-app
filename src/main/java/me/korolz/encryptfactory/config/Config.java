package me.korolz.encryptfactory.config;

import me.korolz.encryptfactory.helpers.KeyManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class Config implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/styles/**")
                .addResourceLocations("classpath:/static/styles/");
    }
    @Bean
    public KeyManager keyManager() {
        return new KeyManager();
    }

    //выполняется при запуске приложения
    @Bean
    public CommandLineRunner loadKey(KeyManager keyManager) {
        return args -> keyManager.loadKey();
    }
}
