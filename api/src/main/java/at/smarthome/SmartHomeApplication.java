package at.smarthome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.smarthome.service.GreeterService;

@SpringBootApplication
//@ComponentScan(basePackages = {"at.fhtw.bic.hellospring", "at.fhtw.bic.hellospring.service", "at.fhtw.bic.hellospring.service.impl" , "at.fhtw.bic.hellospring.config"})
@RestController
public class SmartHomeApplication {

	private final GreeterService greeterService;

	@Autowired
	public SmartHomeApplication(GreeterService greeterService) {
		this.greeterService = greeterService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SmartHomeApplication.class, args);
	}

	@GetMapping("/hello")
	public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
		return String.format("%s %s!", greeterService.sayHello(), name);
	}

	@GetMapping("/goodbye")
	public String sayGoodbye(@RequestParam(value = "myName", defaultValue = "World") String name) {
		return String.format("%s %s!", greeterService.sayGoodbye(), name);
	}
}
