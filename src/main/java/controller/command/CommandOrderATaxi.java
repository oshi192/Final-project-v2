package controller.command;

import exception.EqualsCityException;
import exception.TaxiNotFoundException;
import model.dao.DaoFactory;
import model.dao.entity.*;
import model.dto.OrderDTO;
import model.service.CarTypeService;
import model.service.DiscountService;
import model.service.OrderService;
import org.apache.log4j.Logger;
import util.ResourceBundleManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
        String confirm = request.getParameter("confirm");
        logger.info("confirm:"+confirm);
        if(confirm !=null && !confirm.isEmpty()){
            factory.createOrderDao().create((Order)(request.getSession().getAttribute("order")));
            request.setAttribute("confirmMSG",ResourceBundleManager.getMessage(ResourceBundleManager.MSG_ORDER_CONFIRM));
            return ResourceBundleManager.getPath(ResourceBundleManager.PAGE_INDEX_PATH);
        }else {
            String comment = request.getParameter("comment");
            String toCity = request.getParameter("toCity");
            String fromCity = request.getParameter("fromCity");
            String carType = request.getParameter("carType");
            try {
                OrderDTO order = orderService.createOrderFromRequest(request);
                if (order.getCityDistance().getToCityId() == order.getCityDistance().getFromCityId()) {
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
        request.setAttribute("price",calculateDistance(order.getCityDistance().getDistanceKm(),order.getCarType(),order.getUser())+" uah");
        request.getSession().setAttribute("order",order);//
    }

    private int calculateDistance(int distance, CarType carType, User user) {
        int price = carType.getPriceOverTheCityKm()*distance;
        Discount discount = new DiscountService().findAllByEndDate();
        return new OrderService().calculatePrice(price,user.getSum(),discount);
    }
}
