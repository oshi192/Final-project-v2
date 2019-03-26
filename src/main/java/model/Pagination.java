package model;

import model.service.GenericService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class Pagination {
    private static Logger logger = Logger.getLogger(Pagination.class);

    public void paginate(HttpServletRequest request, GenericService service) {//todo search
        String cp = request.getParameter("currentPage");
        String rp = request.getParameter("recordsPerPage");
        logger.info("pagination before: currentPage-" + cp + " recordsPerPage-" + rp);
        int currentPage = (cp == null || cp.isEmpty()) ? 1 : Integer.valueOf(cp);
        int recordsPerPage = (rp == null || rp.isEmpty()) ? 10 : Integer.valueOf(rp);
        int rows = service.getNumberOfRows();
        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }
        if (recordsPerPage > 15) recordsPerPage = 15;
        if (recordsPerPage < 5) recordsPerPage = 5;
        if (currentPage > nOfPages) currentPage = nOfPages;
        if (currentPage < 1) currentPage = 1;
        request.setAttribute("entity", service.find(currentPage, recordsPerPage));
        logger.info(service.find(currentPage, recordsPerPage));
        logger.info("pagination after: nOfPages-" + nOfPages + "currentPage-" + cp + " recordsPerPage-" + rp);
        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
    }

}
