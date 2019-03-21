package model.service;

import exception.TaxiNotFoundException;
import model.dao.DaoFactory;
import model.dao.entity.CityDistance;
import model.dao.entity.Order;
import model.dao.entity.Taxi;
import model.dao.entity.User;
import model.dto.OrderDTO;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public class OrderService implements GenericService<Order> {
    private DaoFactory factory = DaoFactory.getInstance();
    @Override
    public List<Order> find(int currentPage, int recordsPerPage) {
        return null;
    }

    @Override
    public int getNumberOfRows() {
        return 0;
    }

    public OrderDTO createOrderFromRequest(HttpServletRequest request) throws TaxiNotFoundException {
        String comment = request.getParameter("comment");
        int toCityId = Integer.parseInt(request.getParameter("toCity"));
        int fromCityId = Integer.parseInt(request.getParameter("fromCity"));
        int carTypeId = Integer.parseInt(request.getParameter("carType"));
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUser((User)request.getSession().getAttribute("user"));
        CityDistance cityDistance = factory.createCityDistanceDao().findByCityIds(fromCityId,toCityId);
        orderDTO.setCityDistance(cityDistance);
        Taxi taxi = factory.createTaxiDao().findByStatus(1);
        if(taxi==null){
            throw new TaxiNotFoundException("Sorry but there are no free taxi");
        }
        orderDTO.setTaxi(taxi);
        orderDTO.setCarTypeId(carTypeId);
        orderDTO.setCarType(factory.createCarTypeDao().findById(carTypeId));
        orderDTO.setComment(comment);
        orderDTO.setTime(LocalDateTime.now());
        orderDTO.setCityDistanceId(cityDistance.getId());
        orderDTO.setOrderStatusId(1);
        return orderDTO;
    }
}
