package com.airline.reservation.user;

import io.vertx.core.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.airline.reservation.*")
public class ReservationForUsersApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReservationForUsersApplication.class, args);
	}

}
