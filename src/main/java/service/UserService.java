package service;

import dao.UserDao;

/**
 * @author Mahsa Alikhani m-58
 */
public class UserService {
    UserDao userDao = new UserDao();
    public boolean userValidation(String username, String password) {
        if(userDao.findUserByUsernameAndPassword(username, password)){
            return true;
        }
        return false;
    }
}
