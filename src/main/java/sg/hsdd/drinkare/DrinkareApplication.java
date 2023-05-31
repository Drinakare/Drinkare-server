package sg.hsdd.drinkare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import java.util.TimeZone;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})

public class DrinkareApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrinkareApplication.class, args);
	}

}
