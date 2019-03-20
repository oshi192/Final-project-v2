<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>

<div class="dropdown">
    <div>
         <label>
          ${msg:getMessage("show")}
         <select name="name" class="btn btn-primary dropdown-toggle">
             <option value="5"><a href="?">5</a></option>
             <option value="10">10</option>
            <option value="25">25</option>
             <option value="50">50</option>
             <option value="100">100</option>
         </select>
           ${msg:getMessage("entries")}
         </label>
     </div>
</div>
<nav>
    <ul class="pagination">
        <li class="page-item">
            <a class="page-link"<c:if test="${currentPage > 1}">href="?currentPage=${currentPage-1}"</c:if>>
               "button-previous"
            </a>
        </li>
        <c:forEach begin="${firstPage}" end="${lastPage}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="active page-item">
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                </c:otherwise>
            </c:choose>
            <a class="page-link" href="?currentPage=${i}">${i}</a></li>
        </c:forEach>
        <li class="page-item">
            <a class="page-link" <c:if test="${lastPage > currentPage}">href="?currentPage=${currentPage+1}"</c:if>>
                "button-next"
            </a>
        </li>
    </ul>
</nav>