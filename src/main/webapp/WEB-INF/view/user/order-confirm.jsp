<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>
<h1>order<h1>
<table class="table">
    <tbody>
        <tr class="table-active">
            <td>car type:</td>
            <td>${carType}</td>
        </tr>
        <tr class="table-active">
            <td>from:</td>
            <td>${from}</td>
        </tr>
        <tr class="table-active">
            <td>to:</td>
            <td>${to}</td>
        </tr>
        <tr class="table-active">
            <td>distance:</td>
            <td>${distance}</td>
        </tr>
        <tr class="table-active">
            <td>car:</td>
            <td>${order.taxi.description}</td>
        </tr>
        <tr class="table-active">
            <td>price:</td>
            <td>${price}</td>
        </tr>
    </tbody><br>
    <form method="POST">
    <button type="submit" class="btn btn-primary " name ="confirm" value ="ok">confirm</button>
    </form>
</table>