<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>
                <li>
                     <a href="${pageContext.request.contextPath}/taxi/taxis">
                          ${msg:getMessage("adminmenu-taxis")}
                     </a>
                </li>
                <li>
                     <a href="${pageContext.request.contextPath}/taxi/discounts">
                          ${msg:getMessage("adminmenu-discounts")}
                     </a>
                </li>
                <li>
                     <a href="${pageContext.request.contextPath}/taxi/car-types">
                          ${msg:getMessage("adminmenu-car-types")}
                     </a>
                </li>
                <li>
                     <a href="${pageContext.request.contextPath}/taxi/order">
                        ${msg:getMessage("adminmenu-cal-a-taxi")}
                     </a>
                </li>