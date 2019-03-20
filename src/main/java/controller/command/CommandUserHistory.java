package controller.command;

import model.Pagination;
import model.service.OrderService;
import util.ResourceBundleManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandUserHistory implements  Command {
    private OrderService service = new OrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pagination pagination = new Pagination();
        pagination.paginate(request,service);
        return ResourceBundleManager.getPath(ResourceBundleManager.PAGE_USER_HISTORY_PATH);
    }
}
