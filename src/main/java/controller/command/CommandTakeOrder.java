package controller.command;

import util.ResourceBundleManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandTakeOrder implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if(POST_METHOD.equals(request.getMethod())){
            //transaction change order status to inprogress if taxi is free
            //change taxi status
            //change user money status//
            //
            //
        }
        return ResourceBundleManager.PAGE_TAKE_ORDER;
    }
}
