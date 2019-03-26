package model.service;

import exception.TaxiNotFoundException;
import model.dao.DaoFactory;
import model.dao.entity.*;
import model.dto.OrderDTO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class OrderService implements GenericService<Order> {
    private DaoFactory factory = DaoFactory.getInstance();
    private static Logger logger = Logger.getLogger(OrderService.class);
    private static Map<Predicate<Integer>, Integer> discountUserMap = new HashMap<>();

    static {
        discountUserMap.put(x -> x > 1000 & x <= 10000, 5);
        discountUserMap.put(x -> x > 10000 & x <= 50000, 10);
        discountUserMap.put(x -> x > 50000 & x <= 100000, 15);
        discountUserMap.put(x -> x > 100000, 20);
    }

    @Override
    public List<Order> find(int currentPage, int recordsPerPage) {
        return null;
    }

    @Override
    public int getNumberOfRows() {
        return 0;
    }

    public OrderDTO createOrderFromRequest(HttpServletRequest request) throws TaxiNotFoundException {
        logger.info("createOrderFromRequest ");
        String comment = request.getParameter("comment");
        int toCityId = Integer.parseInt(request.getParameter("toCity"));
        int fromCityId = Integer.parseInt(request.getParameter("fromCity"));
        int carTypeId = Integer.parseInt(request.getParameter("carType"));
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUser((User) request.getSession().getAttribute("user"));
        CityDistance cityDistance = factory.createCityDistanceDao().findByCityIds(fromCityId, toCityId);
        logger.info("found cityDistance : " + cityDistance);
        Taxi taxi = factory.createTaxiDao().findByStatus(1);
        logger.info("found taxi : " + taxi);
        if (taxi == null) {
            throw new TaxiNotFoundException("Sorry but there are no free taxi");
        }//todo change
        orderDTO.setCityDistance(cityDistance);
        orderDTO.setTaxi(taxi);
        orderDTO.setCarTypeId(carTypeId);
        orderDTO.setCarType(factory.createCarTypeDao().findById(carTypeId));
        orderDTO.setComment(comment);
        orderDTO.setTime(LocalDateTime.now());
        orderDTO.setCityDistanceId(cityDistance.getId());
        orderDTO.setOrderStatusId(1);
        orderDTO.setUserId(((User) (request.getSession().getAttribute("user"))).getId());
        logger.info("\t\t\t" + orderDTO);
        return orderDTO;
    }

    public int calculatePrice(int priceTravel, int userSum, Discount discount) {
        int k = discount!=null ? discount.getPercent() : 0;
        int priceResult = 0;
        if (discount!=null) {
            if (discount.getIsSumWithUserDisc()) {
                priceResult = (priceTravel - priceTravel * discountUserMap.get(userSum) / 100 - priceTravel * k / 100);
            } else {
                priceResult = (priceTravel - priceTravel * k / 100);
            }
        } else {
            priceResult = priceTravel - priceTravel * discountUserMap.get(userSum) / 100;
        }
        return priceResult;
    }
}
