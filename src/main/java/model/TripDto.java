package model;

import lombok.Data;
import model.enumeration.BusType;

import java.util.Date;

/**
 * @author Mahsa Alikhani m-58
 */
@Data
public class TripDto {
     private int code;
     private String corporationName;
     private BusType busType;
     private Date departureTime;
     private double price;
     private int remainingSeats;
}
