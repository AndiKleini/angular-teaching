package at.smarthome.service;

import java.util.concurrent.CompletableFuture;

public interface IChuckNorrisService {

    CompletableFuture<String> getRandomFact();
}
