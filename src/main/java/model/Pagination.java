package model;

import model.service.GenericService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class Pagination {
    private static Logger logger = Logger.getLogger(Pagination.class);

    public void paginate(HttpServletRequest request, GenericService service){//todo search
        String cp = request.getParameter("currentPage");
        String rp = request.getParameter("recordsPerPage");
        logger.info("pagination before: currentPage-"+cp+" recordsPerPage-"+rp);
        int currentPage = (cp == null || cp.isEmpty())?1:Integer.valueOf(cp);
        int recordsPerPage = (rp == null || rp.isEmpty())?10:Integer.valueOf(rp);
        request.setAttribute("entity", service.find(currentPage,recordsPerPage));
        int rows = service.getNumberOfRows();
        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }
        logger.info("pagination after: nOfPages-"+nOfPages+"currentPage-"+cp+" recordsPerPage-"+rp);
        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
    }

}
