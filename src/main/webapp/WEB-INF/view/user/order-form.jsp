<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>
<form method='POST'>
<div class="form-group">
    <label for="exampleFormControlSelect1">From</label>
    <select class="form-control" id="exampleFormControlSelect1" name="fromCity">
        <c:forEach var="city" items="${requestScope.cities}" >
            <option value="${city.id}">${city.cityName}</option>
        </c:forEach>
    </select>
</div>
<div class="form-group">
    <label for="exampleFormControlSelect1">to</label>
    <select class="form-control" id="exampleFormControlSelect1" name="toCity">
        <c:forEach var="city" items="${requestScope.cities}" >
            <option value="${city.id}">${city.cityName}</option>
        </c:forEach>
    </select>
</div>
<div class="form-group">
    <label for="exampleFormControlSelect1">Car type</label>
    <select class="form-control" id="exampleFormControlSelect1" name="carType">
        <c:forEach var="carType" items="${requestScope.carTypes}" >
            <option value="${carType.id}">${carType.carTypeName}</option>
        </c:forEach>
    </select>
</div>
<div class="form-group">
    <label for="exampleFormControlTextarea1" name="comment">Comment</label>
    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
</div>
 <button type="submit" class="btn btn-primary ">Order</button>
</form>