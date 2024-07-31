package me.korolz.encryptfactory.config;

import me.korolz.encryptfactory.helpers.KeyManager;
import me.korolz.encryptfactory.helpers.TokenManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<APIFilter> apiFilter() {
        FilterRegistrationBean<APIFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new APIFilter());
        registrationBean.addUrlPatterns("/api/v1/*"); // Применяем фильтр к необходимым URL
        return registrationBean;
    }

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

    @Bean
    public TokenManager tokenManager() {
        return new TokenManager();
    }

    //выполняется при запуске приложения
    @Bean
    public CommandLineRunner loadCredentials(KeyManager keyManager, TokenManager tokenManager) {
        return args -> {
            keyManager.loadKey();
            tokenManager.loadToken();
        };
    }
}
