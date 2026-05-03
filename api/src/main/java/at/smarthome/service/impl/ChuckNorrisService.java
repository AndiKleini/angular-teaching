package at.smarthome.service.impl;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import at.smarthome.service.IChuckNorrisService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChuckNorrisService implements IChuckNorrisService {

    private final RestClient client = RestClient.create("https://api.chucknorris.io");

    @Override
    public CompletableFuture<String> getRandomFact() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                ChuckNorrisFact fact = client.get()
                    .uri("/jokes/random")
                    .retrieve()
                    .body(ChuckNorrisFact.class);
                return fact != null ? fact.value() : null;
            } catch (Exception e) {
                log.warn("ChuckNorrisService: could not fetch fact ({})", e.getMessage());
                return null;
            }
        });
    }

    static record ChuckNorrisFact(String value) { }
}
