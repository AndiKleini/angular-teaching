package at.smarthome.config;

import at.smarthome.service.GreeterService;
import at.smarthome.service.LanguageService;
import at.smarthome.service.impl.GreeterServiceImpl;
import at.smarthome.service.impl.LanguageServiceDE;
import at.smarthome.service.impl.LanguageServiceEN;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    @Value("${greeter.language}")
    private String language;    // the value for this field is taken from application.properties

    @Bean
    public LanguageService languageService() {
        if ( "DE".equals(language))
            return new LanguageServiceDE();
        return new LanguageServiceEN();
    }

    @Bean
    public GreeterService greeterService() {
        return new GreeterServiceImpl( languageService() ); // variant 1: constructor-based dependency-injection via Configuration-Bean
    }
}
