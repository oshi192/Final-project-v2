package model.service;

import exception.UserAlreadyExistException;
import model.dao.CarTypeDao;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dao.entity.CarType;
import model.dao.entity.User;

import java.util.List;

public class CarTypeService implements GenericService<User>{
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public List<CarType> getAll() {
        try (CarTypeDao dao = daoFactory.createCarTypeDao()) {
     return dao.findAll();
        } catch (UserAlreadyExistException ex) {
            return null;
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
