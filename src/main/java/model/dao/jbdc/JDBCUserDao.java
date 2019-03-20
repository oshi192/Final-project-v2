package model.dao.jbdc;

import exception.InputException;
import exception.UserAlreadyExistException;
import exception.UserNotFoundException;
import model.dao.UserDao;
import model.dao.entity.User;
import model.dao.jbdc.mapper.UserMapper;
import org.apache.log4j.Logger;
import util.ResourceBundleManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements UserDao {
    static final Logger logger = Logger.getLogger(JDBCUserDao.class);
    private UserMapper mapper = new UserMapper();

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        String query = ResourceBundleManager.getSqlString("user-findByEmail");
        logger.info(query);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, email);
            logger.info(query +"\n"+ st);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return getUser(st);
            } else {
                throw new InputException("Invalid name or password");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User create(User user) {
        final String queryInsertUser = ResourceBundleManager.getSqlString("user-create");
        try {
            Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
            try {
                connection.setAutoCommit(false);
                PreparedStatement ps = connection.prepareStatement(queryInsertUser, Statement.RETURN_GENERATED_KEYS);
                new UserMapper().putIntoPrepareStatement(ps, user);
                ps.executeUpdate();
                connection.commit();
            } catch (SQLException ex) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                throw new UserAlreadyExistException("register.userExist");
            }
        }catch (SQLException ex){
            throw new RuntimeException("cannot get connection");
        }
        return user;
    }

    @Override
    public User findById(int id) {
        final String query = ResourceBundleManager.getSqlString(ResourceBundleManager.USER_BY_ID);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, id);
            return getUser(st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String query = ResourceBundleManager.getSqlString(ResourceBundleManager.USER_ALL);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                users.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    @Override
    public int count() {
        int result = 0;
        String query = ResourceBundleManager.getSqlString(ResourceBundleManager.USER_COUNT);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private User getUser(PreparedStatement st) throws SQLException {
        User user = new User();
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            user = new UserMapper().extractFromResultSet(rs);
        }
        return user;
    }
}