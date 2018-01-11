package bookstore.user.service;

import bookstore.user.dao.UserDao;
import bookstore.user.domain.User;

import java.sql.SQLException;
import java.util.UUID;

public class UserService {

    private User user;
    private UserDao userDao = new UserDao();

    public User login(User u){
        try {
            user = userDao.queryByUsername(u.getUsername());
            if (user != null){
                if (user.getPassword().equals(u.getPassword())) {
                    return user;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String register(User user){

        userDao.insertUser(user);
        try {
            User u = userDao.queryByUsername(user.getUsername());
//            System.out.println(u.toString() + "................");
            return u.getCode();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String alterUser(String code){

        try {
            User u = userDao.queryByCode(code);
            if (u != null){
                try {
                    userDao.alterUser(code);
//                    System.out.println(u.toString() + "----------");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return "Successful Activation";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }


}
