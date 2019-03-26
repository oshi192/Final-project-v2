package comtroller.command;

import controller.command.CommandRegister;
import model.dao.entity.User;
import model.dao.jbdc.JDBCUserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;

public class CommandRegisterTest {
    private CommandRegister commandRegister = new CommandRegister();
    private HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    private HttpServletResponse responce = Mockito.mock(HttpServletResponse.class);
    private HttpSession session = Mockito.mock(HttpSession.class);
    private ServletContext context = Mockito.mock(ServletContext.class);
    private ConcurrentHashMap<Integer, HttpSession> users = new ConcurrentHashMap<>();


    @Before
    public void setUp() {
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getAttribute("logged-users")).thenReturn(users);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getParameter("username")).thenReturn("testname");
        Mockito.when(request.getParameter("password")).thenReturn("testpass");
        Mockito.when(request.getParameter("first-name")).thenReturn("");
        Mockito.when(request.getParameter("last-name")).thenReturn("");
        Mockito.when(request.getParameter("first-name-en")).thenReturn("");
        Mockito.when(request.getParameter("last-name-en")).thenReturn("");
    }
    @Test
    public void process() {
        String uri = commandRegister.execute(request,responce);
        assertEquals("/servlet/guest/login", uri);

    }
    @After
    public void tearDown() throws SQLException {
        new JDBCUserDao().findByEmail(((User)(request.getAttribute("newUser"))).getEmail());
    }
}
