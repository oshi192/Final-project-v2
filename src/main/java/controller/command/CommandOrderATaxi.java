package controller.command;

import exception.EqualsCityException;
import exception.TaxiNotFoundException;
import model.dao.DaoFactory;
import model.dao.entity.CarType;
import model.dao.entity.City;
import model.dao.entity.User;
import model.dto.OrderDTO;
import model.service.CarTypeService;
import model.service.OrderService;
import org.apache.log4j.Logger;
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
        if (POST_METHOD.equals(request.getMethod())) {
            path = executePost(request);
        } else {
            path = executeGet(request);
        }
        return path;
    }

    private String executeGet(HttpServletRequest request) {
        List<CarType> carTypes = carTypeService.getAll();
        List<City> cities = factory.createCityDao().findAll();
        request.setAttribute("carTypes", carTypes);
        request.setAttribute("cities", cities);
        request.getSession().setAttribute("carTypes", carTypes);
        request.getSession().setAttribute("cities", cities);
        logger.info("GET cities:" + cities.size() + " carTypes:" + carTypes.size());
        return ResourceBundleManager.getPath(ResourceBundleManager.PAGE_ORDER_A_TAXI);
    }

    private String executePost(HttpServletRequest request) {
        String comment = request.getParameter("comment");
        String toCity = request.getParameter("toCity");
        String fromCity = request.getParameter("fromCity");
        String carType = request.getParameter("carType");
        try {
            OrderDTO order = orderService.createOrderFromRequest(request);
            if(order.getCityDistance().getToCityId() == order.getCityDistance().getFromCityId()){
                throw new EqualsCityException("equals city");
            }
            setConfirmParameters(request);
        } catch (TaxiNotFoundException e) {
            request.setAttribute("errorMessage", "sorry" + e);
        } catch (EqualsCityException e) {
            request.setAttribute("errorMessage", "equals city!!");
        }
        logger.info("POST order fromCity:" + fromCity + " toCity:" + toCity + " carType:" + carType + " comment:" + comment + " " + request.getSession().getAttribute("carTypes"));

        return ResourceBundleManager.getPath(ResourceBundleManager.PAGE_ORDER_A_TAXI);
    }

    private void setConfirmParameters(HttpServletRequest request) throws TaxiNotFoundException {
        OrderDTO order = orderService.createOrderFromRequest(request);
        request.setAttribute("order", order);
        request.setAttribute("from", ((List<City>) request.getSession().getAttribute("cities"))
                .stream()
                .filter(x -> x.getId() == order.getCityDistance().getFromCityId())
                .findFirst()
                .get()
                .getCityName()
        );
        request.setAttribute("to", ((List<City>) request.getSession().getAttribute("cities"))
                .stream()
                .filter(x -> x.getId() == order.getCityDistance().getToCityId())
                .findFirst()
                .get()
                .getCityName()
        );
        request.setAttribute("carType", ((List<CarType>) request.getSession().getAttribute("carTypes"))
                .stream()
                .filter(x -> x.getId() == order.getCarTypeId())
                .findFirst()
                .get()
                .getCarTypeName()
        );
        request.setAttribute("distance",order.getCityDistance().getDistanceKm());
        request.setAttribute("price",1/*calculateDistance(order.getCityDistance().getDistanceKm(),order.getCarType(),order.getUser())*/);
        factory.createOrderDao().create(order);
    }

    private int calculateDistance(int distance, CarType carType, User user) {
        return carType.getPriceOverTheCityKm()*distance/100;//todo rewrite
    }
}
