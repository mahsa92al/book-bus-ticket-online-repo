package model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Mahsa Alikhani m-58
 */
@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Bus bus;
    @OneToOne
    private Passenger passenger;
}
