package comtroller.command;

import controller.command.CommandLogin;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import util.LogInOutUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;

public class CommandLoginTest {
    @Test
    public void execute() {
    }

    private CommandLogin commandLogin = new CommandLogin();
    private HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    private HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    private HttpSession session = Mockito.mock(HttpSession.class);
    private ServletContext context = Mockito.mock(ServletContext.class);
    private ConcurrentHashMap<Integer, HttpSession> users = new ConcurrentHashMap<>();
    private LogInOutUtils logInOutUtils = Mockito.mock(LogInOutUtils.class);




    @Before
    public void setUp() {
        Mockito.when(logInOutUtils.getLoggedUsers(request)).thenReturn(users);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(request.getMethod()).thenReturn("POST");
        Mockito.when(context.getAttribute("loggedUsers")).thenReturn(users);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getParameter("email")).thenReturn("user@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn("Userpass1");
    }

    @Test
    public void process() {
//        String page = commandLogin.execute(request, response);
        //assertEquals("redirect:", page);
    }
}
