package controller.command;

import model.dao.entity.User;
import model.dao.jbdc.JDBCDaoFactory;
import org.apache.log4j.Logger;
import util.LogInOutUtils;
import util.ResourceBundleManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class CommandLogin implements Command {
    private static Logger logger = Logger.getLogger(CommandLogin.class);
    private static LogInOutUtils utils = new LogInOutUtils();
private static String PARAMETER_EMAIL ="email";
private static String PARAMETER_PASSWORD ="password";
private static String MSG_EMAIL_ERROR ="emailMessage";
private static String MSG_PASSWORD_ERROR ="passwordMessage";
private static String REDIRECT ="redirect:";
private static String USER ="user";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if(POST_METHOD.equals(request.getMethod())){
            return checkPageData(request);
        }else{
            return ResourceBundleManager.getPath(ResourceBundleManager.PAGE_LOGIN_PATH);
        }
    }

    private String checkPageData(HttpServletRequest request) {
        String email = request.getParameter(PARAMETER_EMAIL);
        String password = request.getParameter(PARAMETER_PASSWORD);
        String page = ResourceBundleManager.getPath(ResourceBundleManager.PAGE_LOGIN_PATH);;
        Map<String,String> messages = new HashMap<>();
        logger.info("input strings: "+email +" "+password);
        if(email == null || email.equals("")){
            messages.put(MSG_EMAIL_ERROR,ResourceBundleManager.getMessage("login-empty-email"));
        }
        if(password == null || password.equals("")){
            messages.put(MSG_PASSWORD_ERROR,ResourceBundleManager.getMessage("login-empty-password"));
        }
        if(messages.isEmpty()){
            User user = new JDBCDaoFactory().createUserDao().findByEmail(email);
            logger.info(""+user + email.equals(user.getEmail())+" "+ password.equals(user.getPassword()));
            if(email.equals(user.getEmail()) & password.equals(user.getPassword())){
                user.setPassword("");
                request.setAttribute(USER,user);
                logIn(request, user);
                logger.info("loggedIn! redirect to home page");
                page = REDIRECT;
            }else{
                messages.put("inputError","wrong email or password!");
            }
        }
        logger.info("errors when log in :"+messages);
        request.setAttribute("messages",messages);
        return page;
    }

    private void logIn(HttpServletRequest request, User user) {
        logger.info(utils);
        Map<Integer, HttpSession> loggedUsers = utils.getLoggedUsers(request);
        logger.info(loggedUsers);
        destroyPreviousSession(loggedUsers, user.getId());
        loggedUsers.put(user.getId(), request.getSession());
        utils.setLoggedUsers(loggedUsers,request);
        sessionSetup(request, user);
    }

    private void destroyPreviousSession(Map<Integer, HttpSession> loggedUsers, int userId) {
        if (loggedUsers.containsKey(userId)) {
            loggedUsers.get(userId).invalidate();
        }
    }
    private void sessionSetup(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
        session.setAttribute("firstName", user.getName());
        session.setAttribute("surName", user.getSurname());
        session.setAttribute("user", user);
        session.setAttribute("userRole", user.getRole());
    }

}
