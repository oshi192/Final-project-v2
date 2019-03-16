package controller;

import controller.command.Command;
import controller.command.CommandHomePage;
import org.apache.log4j.Logger;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/taxi/*", loadOnStartup = 1)
public class MainServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(MainServlet.class);
    private final static String EMPTY = "";
    private final static String REDIRECT = "redirect:";
    private final static String SERVLET = ".*/taxi";
    private static ServletContext context;

    public static ServletContext getContext() {
        return context;
    }

    public void init(ServletConfig servletConfig) {
        context = servletConfig.getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        logger.info("path: " + path);
        path = path.replaceAll(SERVLET, EMPTY);
        Command command = CommandMannager.getCommands().getOrDefault(path, new CommandHomePage());
        logger.info(command.getClass().getName());
        String page = command.execute(request, response);
        logger.info("path2: " + path + " >> page: " + page + " ");
        if (page.contains(REDIRECT)) {
            page = page.replaceAll(REDIRECT, EMPTY);
            logger.info("redirect to page: " + page);
            response.sendRedirect(page);
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
