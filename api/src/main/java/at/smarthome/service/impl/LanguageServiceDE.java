package at.smarthome.service.impl;

import at.smarthome.service.LanguageService;

public class LanguageServiceDE implements LanguageService {
    @Override
    public String hello() {
        return "Hallo";
    }

    @Override
    public String goodbye() {
        return "Auf Wiedersehen!";
    }

    @Override
    public String goodMorning() {
        return "Guten Morgen";
    }

    @Override
    public String goodEvening() {
        return "Guten Abend";
    }

    @Override
    public String goodNight() {
        return "Gute Nacht";
    }
}
