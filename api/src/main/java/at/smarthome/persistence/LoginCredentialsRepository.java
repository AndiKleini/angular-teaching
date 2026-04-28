package at.smarthome.persistence;

import org.springframework.data.repository.CrudRepository;

import at.smarthome.persistence.entities.LoginCredentials;

public interface LoginCredentialsRepository extends CrudRepository<LoginCredentials, Long> { }
