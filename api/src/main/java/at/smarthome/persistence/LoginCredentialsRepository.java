package at.smarthome.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import at.smarthome.persistence.entities.LoginCredentials;

public interface LoginCredentialsRepository extends CrudRepository<LoginCredentials, Long> {

    List<LoginCredentials> findByUsernameContainingIgnoreCase(String usernameSubstring);

    List<LoginCredentials> findByIdGreaterThan(Long threshold);
}
