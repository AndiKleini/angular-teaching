package at.smarthome.service.impl;

import at.smarthome.service.LanguageService;

public class LanguageServiceEN implements LanguageService {
    @Override
    public String hello() {
        return "Hello";
    }

    @Override
    public String goodbye() {
        return "Good bye!";
    }

    @Override
    public String goodMorning() {
        return "Good Morning";
    }

    @Override
    public String goodEvening() {
        return "Good Evening";
    }

    @Override
    public String goodNight() {
        return "Good Night";
    }
}
