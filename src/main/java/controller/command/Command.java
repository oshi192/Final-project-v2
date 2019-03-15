package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    String POST_METHOD = "POST";
    String execute(HttpServletRequest request, HttpServletResponse response);
}
