package controller.filter;

import model.dao.entity.Role;
import model.dao.entity.User;
import org.apache.log4j.Logger;
import util.AccessMapper;
import util.ResourceBundleManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * This class check access rights to page
 */
@WebFilter(filterName = "RoleFilter", urlPatterns = {"/taxi/*"})
public class RoleFilter implements Filter {
    private static final String USER_ATTR = "user";
    private final static Logger logger = Logger.getLogger(RoleFilter.class);

    public RoleFilter() {
    }

    public void destroy() {
    }


    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        logger.info("------- starting Role filtering -------");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER_ATTR);
        Role role;
        if (Objects.isNull(user)) {
            role = new Role(1, "GUEST");
        } else {
            role = user.getRole();
        }
        AccessMapper mapper = AccessMapper.getInstance();
        boolean enoughRights = mapper.checkRights(req, role.getName());
        logger.info("\trole: " + role.getName() + " enoughRights: " + enoughRights);
        if (!enoughRights) {
            String page = ResourceBundleManager.getPath(ResourceBundleManager.PAGE_INDEX_PATH);
            logger.info("\tgo back: " + page);
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            try {
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        logger.info("------- ending Role filtering -------");
        chain.doFilter(request, response);
    }


    public void init(FilterConfig fConfig) throws ServletException {
    }

}
