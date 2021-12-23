package service;

import dao.PassengerDao;
import model.Passenger;

/**
 * @author Mahsa Alikhani m-58
 */
public class PassengerService {
    PassengerDao passengerDao = new PassengerDao();
    public void saveNewPassenger(Passenger passenger) {
        passengerDao.saveNewPassenger(passenger);
    }
}
