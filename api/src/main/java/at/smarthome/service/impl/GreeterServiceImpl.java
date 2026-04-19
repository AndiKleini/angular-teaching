package at.smarthome.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.smarthome.service.GreeterService;
import at.smarthome.service.LanguageService;

import java.time.LocalDateTime;

@Service
@Slf4j  // used for logging (lombok, log4J); provides the static member log
public class GreeterServiceImpl implements GreeterService {
    private final LanguageService languageService;

    @Autowired  // variant 2: constructor-based dependency-injection via Autowired
    public GreeterServiceImpl(LanguageService languageService) {
        this.languageService = languageService;
    }


    // Annotate this method to execute it automatically as the bean is instantiated
    @PostConstruct
    public void init() throws Exception
    {
        log.info( "Bean GreeterServiceImpl hast been instantiated" );
    }

    // Annotate this method to execute it when Spring container is closed
    @PreDestroy
    public void destroy() throws Exception
    {
        log.info( "Bean GreeterServiceImpl has been closed");
    }

    @Override
    public String sayHello() {
        int hourOfDay = LocalDateTime.now().getHour();
        if ( hourOfDay>5 && hourOfDay<10 )
            return languageService.goodMorning();
        else if ( hourOfDay>=17 && hourOfDay<21 )
            return languageService.goodEvening();
        else if ( hourOfDay>=21 )
            return languageService.goodNight();
        else
            return languageService.hello();
    }

    @Override
    public String sayGoodbye() {
        return languageService.goodbye();
    }
}
