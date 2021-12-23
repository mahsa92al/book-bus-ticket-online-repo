package service;

import dao.BusDao;
import model.BusDto;
import model.enumeration.BusType;

import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class BusService {
    BusDao busDao = new BusDao();
    public List<BusDto> getBusReportsByType(BusType busType) {
        List<BusDto> busReportsList = busDao.findBusReportsByType(busType);
        return busReportsList;
    }
}
