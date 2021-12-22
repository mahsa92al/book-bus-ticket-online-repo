package service;

import dao.AdminDao;

/**
 * @author Mahsa Alikhani m-58
 */
public class AdminService {
    AdminDao adminDao = new AdminDao();
    public boolean adminValidation(String username, String password) {
        if(adminDao.findAdminByUsernameAndPassword(username, password)){
            return true;
        }
        return false;
    }
}
