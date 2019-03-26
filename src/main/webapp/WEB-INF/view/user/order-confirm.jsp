<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>
<h1>order<h1>
<table class="table">
    <tbody>
        <tr>
            <td>${msg:getMessage("order-confirm-cartype")}</td>
            <td>${carType}</td>
        </tr>
        <tr>
            <td>${msg:getMessage("order-confirm-from")}</td>
            <td>${from}</td>
        </tr>
        <tr>
            <td>${msg:getMessage("order-confirm-to")}</td>
            <td>${to}</td>
        </tr>
        <tr>
            <td>${msg:getMessage("order-confirm-distance")}</td>
            <td>${distance}</td>
        </tr>
        <tr>
            <td>${msg:getMessage("order-confirm-car")}</td>
            <td>${order.taxi.description}</td>
        </tr>
        <tr>
            <td>${msg:getMessage("order-confirm-price")}</td>
            <td>${price}</td>
        </tr>
        <tr><td><form method="POST">
                <button type="submit" class="btn btn-primary " name ="confirm" value ="ok">confirm</button>
                </form></td>
                </tr>
    </tbody>
</table>