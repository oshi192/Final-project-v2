package model.service;

import exception.UserAlreadyExistException;
import exception.UserNotFoundException;
import model.dao.DaoFactory;
import model.dao.entity.User;
import model.dao.UserDao;

import java.util.List;

public class UserService implements GenericService<User>{
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public boolean isEmailExist(String email) {
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.findByEmail(email);
            return true;
        } catch (UserNotFoundException ex) {
            return false;
        }
    }

    public User registerNewUser(User user) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.create(user);
        }
    }

    @Override
    public List<User> find(int currentPage, int recordsPerPage) {
        return null;
    }

    @Override
    public int getNumberOfRows() {
        return 0;
    }
}
