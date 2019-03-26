package controller.command;

import util.LogInOutUtils;
import util.ResourceBundleManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandLogout implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        new LogInOutUtils().logOut(request);
        return ResourceBundleManager.getPath(ResourceBundleManager.PAGE_INDEX_PATH);
    }
}
