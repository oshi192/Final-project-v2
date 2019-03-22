<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>

                <li>
                     <a href="${pageContext.request.contextPath}/taxi/history">
                        ${msg:getMessage("usermenu-travel-history")}
                     </a>
                </li>
                <li>
                     <a href="${pageContext.request.contextPath}/taxi/order">
                        ${msg:getMessage("usermenu-cal-a-taxi")}call a taxi
                     </a>
                </li>
                <li>
                     <a href="${pageContext.request.contextPath}/taxi/car-types">
                        ${msg:getMessage("usermenu-car-types")}
                     </a>
                </li>
                <li>
                     <a href="${pageContext.request.contextPath}/taxi/discounts">
                        ${msg:getMessage("usermenu-current-discounts")}
                     </a>
                </li>
