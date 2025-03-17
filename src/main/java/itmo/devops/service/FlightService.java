package itmo.devops.service;

import itmo.devops.model.Flight;
import itmo.devops.repository.FlightRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FlightService {

    private final FlightRepository repository;

    @Autowired
    public FlightService(FlightRepository repository) {
        this.repository = repository;
    }

    @SneakyThrows
    public void createFlight(Flight flight) {
        System.out.println("pupu");
        log.info("Сохранить запись flight = {} в БД", flight);
        try {
            repository.save(flight);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Не удалось сохранить запись в БД");
        }
    }
}
