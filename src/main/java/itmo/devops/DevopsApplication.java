package itmo.devops;

import itmo.devops.model.Flight;
import itmo.devops.repository.FlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"itmo.devops"})
@EntityScan(value = "itmo.devops.model")
@EnableJpaRepositories("itmo.devops.repository")
@SpringBootApplication
public class DevopsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevopsApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(FlightRepository repository) {
        return (args) -> {
            Flight flight = new Flight();
            flight.setDestination("dest");
            flight.setNumber("number");
            flight.setOrigin("origin");

            repository.save(flight);
        };
    }
}
