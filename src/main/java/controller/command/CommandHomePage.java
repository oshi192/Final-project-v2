package controller.command;

import util.ResourceBundleManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandHomePage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return ResourceBundleManager.getPath(ResourceBundleManager.PAGE_INDEX_PATH);
    }
}
