package itmo.devops.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "flight")
public class Flight {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_id_seq")
    @SequenceGenerator(name = "flight_id_seq", allocationSize = 1)
    @Id
    private Long id;

    private String number;

    private String origin;

    private String destination;
}
