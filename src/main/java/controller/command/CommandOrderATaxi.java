package controller.command;

import controller.MainServlet;
import exception.TaxiNotFoundException;
import model.dao.DaoFactory;
import model.dao.entity.CarType;
import model.dao.entity.City;
import model.dao.entity.Order;
import model.dao.jbdc.JDBCCityDao;
import model.service.CarTypeService;
import model.service.OrderService;
import model.service.TaxiService;
import org.apache.log4j.Logger;
import org.w3c.dom.stylesheets.LinkStyle;
import util.ResourceBundleManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CommandOrderATaxi implements Command {
    private DaoFactory factory = DaoFactory.getInstance();
    private CarTypeService carTypeService = new CarTypeService();
    private OrderService orderService = new OrderService();

    private static Logger logger = Logger.getLogger(CommandOrderATaxi.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String path;
        if(POST_METHOD.equals(request.getMethod())){
            path = executePost(request);
        }else{
            path = executeGet(request);
        }
        return path;
    }

    private String executeGet(HttpServletRequest request) {
        List<CarType> carTypes = carTypeService.getAll();
        List<City> cities =factory.createCityDao().findAll();
        request.setAttribute("carTypes",carTypes);
        request.setAttribute("cities",cities);
        request.getSession().setAttribute("carTypes",carTypes);
        request.getSession().setAttribute("cities",cities);
        return ResourceBundleManager.getPath(ResourceBundleManager.PAGE_ORDER_A_TAXI);
    }

    private String executePost(HttpServletRequest request) {
        String comment = request.getParameter("comment");
        String toCity = request.getParameter("toCity");
        String fromCity = request.getParameter("fromCity");
        String carType = request.getParameter("carType");
        try {
            Order order = orderService.createOrderFromRequest(request);
            request.setAttribute("order",order);
            factory.createOrderDao().create(order);
        } catch (TaxiNotFoundException e) {
            request.setAttribute("errorMessage", "sorry"+e);
        }
        logger.info("POST order fromCity:"+fromCity+" toCity:"+toCity+" carType:"+carType+" comment:"+comment+" "+request.getSession().getAttribute("carTypes"));

        return ResourceBundleManager.getPath(ResourceBundleManager.PAGE_ORDER_A_TAXI);
    }
}
