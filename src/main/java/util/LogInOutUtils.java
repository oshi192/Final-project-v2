package util;

import controller.MainServlet;
import model.dao.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * class for logout user
 */
public class LogInOutUtils {
    private static Logger logger = Logger.getLogger(LogInOutUtils.class);

    public void logOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<Integer, HttpSession> loggedUsers = getLoggedUsers(request);
        loggedUsers.remove(getUserId(session));
        setLoggedUsers(loggedUsers, request);
        session.removeAttribute("user");
    }

    private Integer getUserId(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user == null ? 0 : user.getId();
    }

    public void setLoggedUsers(Map<Integer, HttpSession> loggedUsers, HttpServletRequest request) {
        request.getSession().setAttribute("loggedUsers", loggedUsers);
    }

    public Map<Integer, HttpSession> getLoggedUsers(HttpServletRequest request) {

        return (ConcurrentHashMap<Integer, HttpSession>) request.getSession().getAttribute("loggedUsers");
    }
}
