package at.smarthome;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import at.smarthome.persistence.entities.LoginCredentials;
import at.smarthome.service.LoginRequest;
import at.smarthome.service.LoginResponse;
import at.smarthome.service.User;

@SpringBootTest(/*classes = SmartHomeApplication.class,*/ webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class UserControllerTests {

    @Test
	void get_user_without_token_returns_forbidden(@Autowired WebTestClient webTestClient) {
		webTestClient
				.get().uri("/user")
				.exchange()
				.expectStatus().isForbidden()
				.expectBody(String.class).isEqualTo(null);
	}

	@Test
	void get_auth_returns_forbidden(@Autowired WebTestClient webTestClient) {
		webTestClient
				.get().uri("/auth")
				.exchange()
				.expectStatus().isForbidden()
				.expectBody(String.class).isEqualTo(null);
	}

	@Test
	void post_auth_invalid_credentials_returns_unauthorized(@Autowired WebTestClient webTestClient) {
		LoginRequest body = new LoginRequest();
		body.setUsername("Helmuth");
		body.setPassword("invalid");
		webTestClient
				.post().uri("/auth").bodyValue(body)
				.exchange()
				.expectStatus().isUnauthorized()
				.expectBody(String.class).isEqualTo(null);
	}

	@Test
	//@Disabled("This test fails because the authentication is not included into the test yet.")
	void post_auth_valid_credentials_returns_success(@Autowired WebTestClient webTestClient) {
		LoginRequest body = new LoginRequest();
		User user = new User("Kaka","Chris");

		webTestClient
				.post().uri("/user").bodyValue(user)
				.exchange()
				.expectStatus().isOk();

		body.setUsername("Kaka");
		body.setPassword("Chris");
		LoginResponse loginResponse = webTestClient
				.post().uri("/auth").bodyValue(body)
				.exchange()
				.expectStatus().isOk()
				.expectBody(LoginResponse.class).returnResult().getResponseBody();
		Assertions.assertThat(loginResponse).isNotNull();
		Assertions.assertThat(loginResponse.getAccessToken()).isNotNull();
		Assertions.assertThat(loginResponse.getFact()).isNotBlank();
	}
}
