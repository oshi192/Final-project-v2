package model.dao;

import exception.UserNotFoundException;
import model.dao.entity.User;

public interface UserDao extends GenericDao<User> {
    User findByEmail(String email) throws UserNotFoundException;
}