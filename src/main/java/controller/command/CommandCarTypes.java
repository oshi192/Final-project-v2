package controller.command;

import model.Pagination;
import model.dao.CarTypeDao;
import model.dao.DaoFactory;
import model.dao.entity.CarType;
import model.dao.jbdc.JDBCCarTypeDao;
import model.dao.jbdc.JDBCDaoFactory;
import model.service.CarTypeService;
import org.apache.log4j.Logger;
import util.ResourceBundleManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CommandCarTypes implements Command {
    private CarTypeService carTypeService = new CarTypeService();
    private static Logger logger = Logger.getLogger(CommandCarTypes.class);
    private static DaoFactory dao = JDBCDaoFactory.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (POST_METHOD.equals(request.getMethod())) {
            executePost(request);
        }
        Pagination pagination = new Pagination();
        pagination.paginate(request, carTypeService);
        return ResourceBundleManager.getPath(ResourceBundleManager.PAGE_CARTYPES);
    }

    private void executePost(HttpServletRequest request) {
        String add = request.getParameter("add");
        logger.info("post");
        if (add != null) {
            addCarType(request);
        }
        String remove = request.getParameter("remove");
        if (remove != null) {
            removeCarType(request);
        }
    }

    private void removeCarType(HttpServletRequest request) {
        String idString = request.getParameter("remove");
        logger.info("removing with id["+idString+"]");
        if (idString !=null) {
            CarTypeDao carTypeDao = dao.createCarTypeDao();
            carTypeDao.delete(Integer.parseInt(idString));
        }

    }

    private void addCarType(HttpServletRequest request) {
        logger.info("add new");
        String carTypeName = request.getParameter("taxi-add-carTypeName");
        String priceCityKmtmp = request.getParameter("taxi-add-priceCityKm");
        String priceOverTheCityKm = request.getParameter("taxi-add-priceOverTheCityKm");
        String priceWaitingTimeMinute = request.getParameter("taxi-add-priceWaitingTimeMinute");
        String priceWaitingTimeFree = request.getParameter("taxi-add-priceWaitingTimeFree");
        Map<String, String> messages = new HashMap<>();
        if (carTypeName == null || carTypeName.isEmpty()) {
            messages.put("MSG-carTypeName", "empty field");//todo resourceBundle
        }
        if (priceCityKmtmp == null || priceCityKmtmp.isEmpty()) {
            messages.put("MSG-priceCityKmtmp", "empty field");//todo resourceBundle
        }
        if (priceOverTheCityKm == null || priceOverTheCityKm.isEmpty()) {
            messages.put("MSG-priceOverTheCityKm", "empty field");//todo resourceBundle
        }
        if (priceWaitingTimeMinute == null || priceWaitingTimeMinute.isEmpty()) {
            messages.put("MSG-priceWaitingTimeMinute", "empty field");//todo resourceBundle
        }
        if (priceWaitingTimeFree == null || priceWaitingTimeFree.isEmpty()) {
            messages.put("MSG-priceWaitingTimeFree", "empty field");//todo resourceBundle
        }
        if (messages.isEmpty()) {
            DaoFactory dao = JDBCDaoFactory.getInstance();
            dao.createCarTypeDao().create(new CarType(0,
                    carTypeName,
                    Integer.parseInt(priceCityKmtmp),
                    Integer.parseInt(priceOverTheCityKm),
                    Integer.parseInt(priceWaitingTimeMinute),
                    Integer.parseInt(priceWaitingTimeFree))
            );
            logger.info("created new car type");
        }
        logger.info("errors: " + messages);

    }
}
