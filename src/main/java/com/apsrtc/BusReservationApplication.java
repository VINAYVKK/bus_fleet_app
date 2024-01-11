package com.apsrtc;

import com.apsrtc.model.Route;
import com.apsrtc.repository.RouteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BusReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusReservationApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(RouteRepository routeRepository) {
		return args -> {

			if (routeRepository.count() == 0) {
				// Add your preloaded data here
				Route route1 = new Route("StartPoint1", "EndPoint1", 100);
				Route route2 = new Route("StartPoint2", "EndPoint2", 150);

				routeRepository.save(route1);
				routeRepository.save(route2);
			}
		};
	}
}