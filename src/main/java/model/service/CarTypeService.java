package model.service;

import exception.UserAlreadyExistException;
import model.dao.CarTypeDao;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dao.entity.CarType;
import model.dao.entity.User;

import java.util.List;

public class CarTypeService implements GenericService<CarType>{
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public List<CarType> getAll() {
        CarTypeDao dao = daoFactory.createCarTypeDao();
     return dao.findAll();

    }

    public User registerNewUser(User user) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.create(user);
        }
    }

    @Override
    public List<CarType> find(int currentPage, int recordsPerPage) {
        CarTypeDao dao = daoFactory.createCarTypeDao();
        return dao.findAll();
    }

    @Override
    public int getNumberOfRows() {
        return 0;
    }
}
