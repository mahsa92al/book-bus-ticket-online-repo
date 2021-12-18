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
    private String origin;
    private String destination;
    @Temporal(TemporalType.DATE)
    private Date departureDate;
    @Temporal(TemporalType.TIME)
    private Date departureTime;
    private String corporationName;
    private double price;
    @ManyToOne
    private Bus bus;
    @OneToOne
    private Passenger passenger;
}
