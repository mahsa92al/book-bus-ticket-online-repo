package model;


import lombok.Data;

import java.util.Date;

/**
 * @author Mahsa Alikhani m-58
 */
@Data
public class BusDto {
    private String busPlate;
    private Date departureDate;
    private int remainingSeats;
    private int soldSeatNumber;
}
