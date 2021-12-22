package model;

import lombok.Data;
import model.enumeration.BusType;

/**
 * @author Mahsa Alikhani m-58
 */
@Data
public class TripDto {
     private String corporationName;
     private BusType busType;
     private String departureTime;
     private double price;
     private int remainingSeats;
}
