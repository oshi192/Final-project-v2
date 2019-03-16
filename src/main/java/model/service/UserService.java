package model.service;

import exception.UserAlreadyExistException;
import model.dao.DaoFactory;
import model.dao.entity.User;
import model.dao.UserDao;

import java.util.List;

public class UserService implements GenericService<User>{
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public boolean isEmailExist(String email) {
        try (UserDao dao = daoFactory.createUserDao()) {
            User user = dao.findByEmail(email);
            return user.getEmail() == null;
        } catch (UserAlreadyExistException ex) {
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
