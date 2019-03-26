package controller.command;

import model.Pagination;
import model.dao.entity.Taxi;
import model.dao.jbdc.JDBCDaoFactory;
import model.service.CarTypeService;
import model.service.TaxiService;
import org.apache.log4j.Logger;
import util.ResourceBundleManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CommandAdminTaxis implements Command {
    private CarTypeService carTypeService = new CarTypeService();
    private TaxiService taxiService = new TaxiService();
    private static Logger logger = Logger.getLogger(CommandAdminTaxis.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("startExecute");
        if (POST_METHOD.equals(request.getMethod())) {
            String rp = request.getParameter("recordsPerPage");
            if (rp == null || rp.isEmpty()) {
                logger.info("taxis recordsPerPage is empty");

            } else {
                logger.info("new recordsPerPage: " + rp);
                request.setAttribute("currentPage", 1);
                request.setAttribute("recordsPerPage", rp);
            }
            String add = request.getParameter("add");
            if (add != null && !add.isEmpty()) {
                addAction(request);
            }
            String remove = request.getParameter("remove");
            if (remove != null && !remove.isEmpty()) {
                removeAction(request);
            }
        }
        request.setAttribute("carTypes", carTypeService.getAll());
        Pagination pagination = new Pagination();
        pagination.paginate(request, taxiService);
        return ResourceBundleManager.getPath(ResourceBundleManager.PAGE_TAXIS_PATH);
    }

    private void addAction(HttpServletRequest request) {
        logger.info("taxis add");
        createTaxiFromRequest(request);
    }

    private void removeAction(HttpServletRequest request) {
        String remove = request.getParameter("remove");
        logger.info("taxis remove with id-" + remove);
        new JDBCDaoFactory().createTaxiDao().delete(Integer.parseInt(remove));
        logger.info("taxis remove successful");
    }

    private void createTaxiFromRequest(HttpServletRequest request) {
        Taxi taxi = new Taxi();
        String description = request.getParameter("taxi-add-description");
        String carType = request.getParameter("taxi-add-cartype");
        Map<String, String> messages = new HashMap<>();
        if (description == null || description.isEmpty()) {
            messages.put("descriptionMsg", "description is empty");
        }
        if (carType == null || carType.isEmpty()) {
            messages.put("carTypeMsg", "car type is empty");
        }
        if (messages.isEmpty()) {
            taxi.setIdcarType(new JDBCDaoFactory().createCarTypeDao().findByCarTypeName(carType).getId());
            taxi.setDescription(description);
            new JDBCDaoFactory().createTaxiDao().create(taxi);
            logger.info("taxi create successful");
        } else {
            logger.info("errors : " + messages);
        }
    }
}
