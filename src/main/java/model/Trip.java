package model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
@Data
@Entity
public class Trip {
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
    private int remainingSeats;
    @ManyToMany(mappedBy = "trips")
    private List<Bus> buses = new ArrayList<>();
    @OneToMany
    private List<Ticket> tickets = new ArrayList<>();
}
