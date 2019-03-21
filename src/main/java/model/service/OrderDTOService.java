package model.service;

import controller.MainServlet;
import model.dao.entity.Order;
import model.dao.jbdc.JDBCOrderDao;
import model.dto.OrderDTO;

import java.util.List;

public class OrderDTOService implements GenericService<OrderDTO> {//todo
    @Override
    public List<OrderDTO> find(int currentPage, int recordsPerPage) {
        JDBCOrderDao dao = (JDBCOrderDao)daoFactory.createOrderDao();

        //List<OrderDTO> orders = dao.findAllByUserIdPaginateDTO(currentPage,recordsPerPage);
        return null;
    }

    @Override
    public int getNumberOfRows() {
        return 0;
    }
}
