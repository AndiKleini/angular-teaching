package at.smarthome;

import java.util.List;

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

	@Test
	void get_user_by_substring_returns_matching_user(@Autowired WebTestClient webTestClient) {
		String token = createUserAndLogin(webTestClient, "Alice", "secret");

		List<User> result = webTestClient
				.get().uri("/user?q=lic")
				.header("Authorization", "Bearer " + token)
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(User.class).returnResult().getResponseBody();

		Assertions.assertThat(result).extracting(User::getName).contains("Alice");
	}

	@Test
	void get_user_by_substring_with_no_match_returns_empty_list(@Autowired WebTestClient webTestClient) {
		String token = createUserAndLogin(webTestClient, "Bob", "secret");

		List<User> result = webTestClient
				.get().uri("/user?q=zzz_no_such_user")
				.header("Authorization", "Bearer " + token)
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(User.class).returnResult().getResponseBody();

		Assertions.assertThat(result).isEmpty();
	}

	@Test
	void get_user_me_returns_authenticated_user(@Autowired WebTestClient webTestClient) {
		String token = createUserAndLogin(webTestClient, "Dave", "secret");

		User result = webTestClient
				.get().uri("/user/me")
				.header("Authorization", "Bearer " + token)
				.exchange()
				.expectStatus().isOk()
				.expectBody(User.class).returnResult().getResponseBody();

		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.getName()).isEqualTo("Dave");
	}

	@Test
	void get_user_me_without_token_returns_forbidden(@Autowired WebTestClient webTestClient) {
		webTestClient
				.get().uri("/user/me")
				.exchange()
				.expectStatus().isForbidden();
	}

	@Test
	void get_user_by_threshold_returns_users_above_id(@Autowired WebTestClient webTestClient) {
		createUserAndLogin(webTestClient, "BeforeCarol", "Rambo");
		String token = createUserAndLogin(webTestClient, "Carol", "secret");

		

		List<User> result = webTestClient
				.get().uri("/user?threshold=1")
				.header("Authorization", "Bearer " + token)
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(User.class).returnResult().getResponseBody();

		Assertions.assertThat(result).extracting(User::getName).contains("Carol");
	}

	private String createUserAndLogin(WebTestClient webTestClient, String username, String password) {
		User user = new User(username, password);
		webTestClient
				.post().uri("/user").bodyValue(user)
				.exchange()
				.expectStatus().isOk();

		LoginRequest login = new LoginRequest();
		login.setUsername(username);
		login.setPassword(password);
		LoginResponse loginResponse = webTestClient
				.post().uri("/auth").bodyValue(login)
				.exchange()
				.expectStatus().isOk()
				.expectBody(LoginResponse.class).returnResult().getResponseBody();
		return loginResponse.getAccessToken();
	}
}
