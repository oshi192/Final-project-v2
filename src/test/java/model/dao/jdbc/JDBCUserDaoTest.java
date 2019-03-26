package model.dao.jdbc;

import exception.NotUniqueValueException;
import exception.UserNotFoundException;
import model.dao.DaoFactory;
import model.dao.entity.Role;
import model.dao.entity.Taxi;
import model.dao.entity.User;
import model.dao.jbdc.JDBCDaoFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JDBCUserDaoTest {
    private DaoFactory daoFactory = JDBCDaoFactory.getInstance();

    @Test
    public void getUserById() {
        User user = daoFactory.createUserDao().findById(1);
        assertTrue("User".equals(user.getName()));
    }
    @Test
    public void getAllUsers(){
        assertTrue(daoFactory.createUserDao().findAll().size()>3);
    }

    @Test(expected = UserNotFoundException.class)
    public void findByEmail(){
        daoFactory.createUserDao().findByEmail( "notexist@gmail.com");
    }
    @Test
    public void findByEmail2(){
        assertEquals(daoFactory.createUserDao().findByEmail( "user@gmail.com").getId(),1);
    }
    @Test(expected = NotUniqueValueException.class)
    public void create(){
        User user = new User(1,1,"user@gmail.com","Usersdfs","pass","name","surname",new Role(1,"USER"));
        daoFactory.createUserDao().create( user);
    }



}
