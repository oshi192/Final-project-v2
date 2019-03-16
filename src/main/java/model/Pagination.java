package model;

import model.service.GenericService;

import javax.servlet.http.HttpServletRequest;

public class Pagination {
    void paginate(HttpServletRequest request, GenericService service){
        int currentPage = Integer.valueOf(request.getParameter("currentPage"));
        int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
        request.setAttribute("entity", service.find(currentPage,recordsPerPage));
        int rows = service.getNumberOfRows();
        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }
        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
    }

}
